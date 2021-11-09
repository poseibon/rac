package com.zwedu.rac.application.interceptor;

import com.google.common.collect.Maps;
import com.zwedu.rac.application.strategy.DataAccessStrategyHandlerBuilder;
import com.zwedu.rac.application.strategy.SessionHelper;
import com.zwedu.rac.application.strategy.StrategyTypeEnum;
import com.zwedu.rac.common.strategy.entity.StrategyInfo;
import com.zwedu.rac.sdk.common.RacContext;
import com.zwedu.rac.sdk.rdo.StrategyRdo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.poseibon.common.enums.ResponseCodeEnum;
import org.poseibon.common.exception.BaseException;

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
public class WriteAuthInterceptor {

    @Around("@annotation(com.zwedu.rac.sdk.common.annotation.WriteAuth)")
    public Object handle(ProceedingJoinPoint pjp) throws Throwable {
        StrategyRdo strategyRdo = StrategyThreadLocal.CURRENT_STRATEGY.get();
        RacContext racContext = RacContext.of().userRdo(SessionHelper.getUserRdo())
                .paramMap(parseArgs(pjp));
        StrategyInfo strategyInfo = StrategyTypeEnum.of(strategyRdo.getType()).getParser()
                .parse(strategyRdo.getExpression());
        boolean hasAuth = DataAccessStrategyHandlerBuilder.instance(strategyInfo.getType())
                .hasAuth(strategyInfo, racContext);
        if (!hasAuth) {
            throw new BaseException(ResponseCodeEnum.NO_AUTH.getValue(), ResponseCodeEnum.NO_AUTH.getText());
        }
        Object result = pjp.proceed();
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