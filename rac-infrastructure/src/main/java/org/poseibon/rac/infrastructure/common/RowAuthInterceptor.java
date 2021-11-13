package org.poseibon.rac.infrastructure.common;


import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.poseibon.common.constant.SeparationChar;
import org.poseibon.common.enums.DataAccessEnum;
import org.poseibon.common.validator.ParamAssert;
import org.poseibon.rac.rowauth.annotation.RowAuthFilter;
import org.poseibon.rac.rowauth.strategy.AuthInfoThreadLocal;
import org.poseibon.rac.rowauth.strategy.vo.AuthInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * mybatis数据权限拦截器
 *
 * @author qingchuan
 * @date 2020/12/26
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class RowAuthInterceptor implements Interceptor {

    private Logger log = LoggerFactory.getLogger(RowAuthInterceptor.class);
    // 缓存
    private static final Map<String, RowAuthFilter> ROW_AUTH_FILTER_CACHE = new ConcurrentHashMap<>();

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation
                .getTarget();
        MetaObject metaObject = MetaObject
                .forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                        SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,
                        new DefaultReflectorFactory());
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        // 获取方法上的数据权限注解，如果没有注解，则直接通过
        RowAuthFilter rowAuthFilter = getPermissionByDelegate(mappedStatement);
        if (rowAuthFilter != null) {
            AuthInfo authInfo = AuthInfoThreadLocal.AUTH_INFO.get();
            // 如果是添加了读权限的接口，dataAccess必须有值，无值直接抛出异常
            if (authInfo != null && DataAccessEnum.ALL.equals(authInfo.getDataAccess())) {
                // do nothing
                log.info("the type of data access is all, do nothing for this query!");
            } else if (authInfo != null && DataAccessEnum.DECENTRALIZED.equals(authInfo.getDataAccess())) {
                ParamAssert.PARAM_EMPTY_ERROR.allNotNull(authInfo.getDbFieldName(), authInfo.getAuthList());
                BoundSql boundSql = statementHandler.getBoundSql();
                String originalSql = boundSql.getSql().trim();
                String authSql = AuthSqlSupporter.getInstance().newSqlWithAuth(originalSql, rowAuthFilter.tblName(),
                        authInfo.getDbFieldName(), authInfo.getAuthList());
                log.info("new sql with auth: " + authSql);
                metaObject.setValue("delegate.boundSql.sql", authSql);
            } else {
                throw new RuntimeException("no auth access!");
            }
        }
        return invocation.proceed();
    }


    /**
     * 获取数据权限注解信息
     *
     * @param statement 清单信息
     * @return 读权限注解
     */
    private RowAuthFilter getPermissionByDelegate(MappedStatement statement) throws ClassNotFoundException {
        String id = statement.getId();
        RowAuthFilter rowAuthFilter = ROW_AUTH_FILTER_CACHE.get(id);
        if (rowAuthFilter != null) {
            return rowAuthFilter;
        }
        String className = id.substring(0, id.lastIndexOf(SeparationChar.DOT));
        String methodName = id.substring(id.lastIndexOf(SeparationChar.DOT) + 1);
        final Class<?> cls = Class.forName(className);
        final Method[] method = cls.getMethods();
        for (Method me : method) {
            if (me.getName().equals(methodName) && me.isAnnotationPresent(RowAuthFilter.class)) {
                rowAuthFilter = me.getAnnotation(RowAuthFilter.class);
                ROW_AUTH_FILTER_CACHE.put(id, rowAuthFilter);
                break;
            }
        }
        return rowAuthFilter;
    }

}
