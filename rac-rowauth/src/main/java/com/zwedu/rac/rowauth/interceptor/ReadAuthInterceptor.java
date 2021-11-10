package com.zwedu.rac.rowauth.interceptor;

import org.poseibon.common.auth.AuthInfo;
import org.poseibon.common.auth.AuthInfoThreadLocal;
import org.poseibon.common.enums.DataAccessEnum;
import com.zwedu.rac.rowauth.enums.StrategyExpressEnum;
import com.zwedu.rac.rowauth.strategy.DataAccessStrategyHandlerBuilder;
import com.zwedu.rac.rowauth.strategy.entity.StrategyInfo;
import com.zwedu.rac.sdk.threadlocal.RacContextThreadLocal;
import com.zwedu.rac.sdk.vo.RacContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.poseibon.common.utils.Args2;
import org.springframework.stereotype.Component;

/**
 * 自定义拦截器-方法拦截器，基于spring aop
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Component
@Aspect
public class ReadAuthInterceptor {

    @Around("@annotation(com.zwedu.rac.rowauth.annotation.ReadAuth)")
    public Object handle(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Object[] argValues = pjp.getArgs();
        String[] argNames = methodSignature.getParameterNames();
        RacContext racContext = RacContextThreadLocal.CURRENT_RAC_CONTEXT.get()
                .paramMap(Args2.parseArgs(argNames, argValues));
        StrategyInfo strategyInfo = StrategyExpressEnum.of(racContext.getStrategyRdo().getType()).getParser()
                .parse(racContext.getStrategyRdo().getExpression());
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

}