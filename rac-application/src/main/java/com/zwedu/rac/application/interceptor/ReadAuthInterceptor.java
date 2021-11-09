package com.zwedu.rac.application.interceptor;

import com.google.common.collect.Maps;
import com.zwedu.rac.application.strategy.DataAccessStrategyHandlerBuilder;
import com.zwedu.rac.application.strategy.SessionHelper;
import com.zwedu.rac.application.strategy.StrategyTypeEnum;
import com.zwedu.rac.common.enums.DataAccessEnum;
import com.zwedu.rac.common.strategy.entity.AuthInfo;
import com.zwedu.rac.common.strategy.entity.StrategyInfo;
import com.zwedu.rac.domain.common.AuthInfoThreadLocal;
import com.zwedu.rac.sdk.common.RacContext;
import com.zwedu.rac.sdk.rdo.StrategyRdo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 自定义拦截器-方法拦截器，基于spring aop
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Slf4j
@Aspect
public class ReadAuthInterceptor {

    @Around("@annotation(com.zwedu.rac.sdk.common.annotation.ReadAuth)")
    public Object handle(ProceedingJoinPoint pjp) throws Throwable {
        StrategyRdo strategyRdo = StrategyThreadLocal.CURRENT_STRATEGY.get();
        RacContext racContext = RacContext.of().userRdo(SessionHelper.getUserRdo())
                .paramMap(parseArgs(pjp));
        StrategyInfo strategyInfo = StrategyTypeEnum.of(strategyRdo.getType()).getParser()
                .parse(strategyRdo.getExpression());
        AuthInfo authInfo = DataAccessStrategyHandlerBuilder.instance(strategyInfo.getType())
                .getAuthInfo(strategyInfo, racContext);
        Object result = null;
        if (DataAccessEnum.ALL.equals(authInfo.getDataAccess())) {
            result = pjp.proceed();
        } else if (DataAccessEnum.DECENTRALIZED.equals(authInfo.getDataAccess())) {
            AuthInfoThreadLocal.AUTH_INFO.set(authInfo);
            try {
                result = pjp.proceed();
            } finally {
                AuthInfoThreadLocal.AUTH_INFO.remove();
            }
        }
        return result;
    }

    /**
     * 解析参数
     *
     * @param pjp 切面对象
     * @return 参数集合
     */
    private Map<String, Object> parseArgs(ProceedingJoinPoint pjp) throws IllegalAccessException {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Object[] argValues = pjp.getArgs();
        String[] argNames = methodSignature.getParameterNames();
        Map<String, Object> argMap = Maps.newHashMap();
        if (argNames != null && argNames.length > 0) {
            for (int i = 0; i < argNames.length; i++) {
                String argName = argNames[i];
                Object argValue = argValues[i];
                argMap.put(argName, argValue);
                Map<String, Object> propertyMap = parseObj(argValue);
                if (MapUtils.isNotEmpty(propertyMap)) {
                    argMap.putAll(propertyMap);
                }
            }
        }
        return argMap;
    }

    /**
     * 解析对象属性值
     *
     * @param obj 对象
     * @return 属性名称和值键值对map
     * @throws IllegalAccessException
     */
    private Map<String, Object> parseObj(Object obj) throws IllegalAccessException {
        if (obj == null) {
            return Maps.newHashMap();
        }
        Map<String, Object> retMap = Maps.newHashMap();
        Class clazz = obj.getClass();
        while (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(obj);
                if (value != null) {
                    retMap.put(field.getName(), value);
                }
            }
            clazz = clazz.getSuperclass();
        }
        return retMap;
    }
}