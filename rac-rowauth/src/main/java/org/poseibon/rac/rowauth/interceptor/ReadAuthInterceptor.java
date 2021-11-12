package org.poseibon.rac.rowauth.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.poseibon.common.utils.Args2;
import org.poseibon.rac.rowauth.enums.StrategyExpressEnum;
import org.poseibon.rac.rowauth.strategy.AuthInfoThreadLocal;
import org.poseibon.rac.rowauth.strategy.DataAccessStrategyHandlerBuilder;
import org.poseibon.rac.rowauth.strategy.vo.AuthInfo;
import org.poseibon.rac.rowauth.strategy.vo.StrategyInfo;
import org.poseibon.rac.sdk.threadlocal.RacContextThreadLocal;
import org.poseibon.rac.sdk.vo.RacContext;
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

    @Around("@annotation(org.poseibon.rac.rowauth.annotation.ReadAuth)")
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
        AuthInfoThreadLocal.AUTH_INFO.set(authInfo);
        try {
            result = pjp.proceed();
        } finally {
            AuthInfoThreadLocal.AUTH_INFO.remove();
        }
        return result;
    }

}