package org.poseibon.rac.rowauth.interceptor;

import org.poseibon.rac.rowauth.enums.StrategyExpressEnum;
import org.poseibon.rac.rowauth.strategy.DataAccessStrategyHandlerBuilder;
import org.poseibon.rac.rowauth.strategy.entity.StrategyInfo;
import org.poseibon.rac.sdk.threadlocal.RacContextThreadLocal;
import org.poseibon.rac.sdk.vo.RacContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.poseibon.common.enums.ResponseCodeEnum;
import org.poseibon.common.exception.BaseException;
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
public class WriteAuthInterceptor {

    @Around("@annotation(org.poseibon.rac.rowauth.annotation.WriteAuth)")
    public Object handle(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Object[] argValues = pjp.getArgs();
        String[] argNames = methodSignature.getParameterNames();
        RacContext racContext = RacContextThreadLocal.CURRENT_RAC_CONTEXT.get()
                .paramMap(Args2.parseArgs(argNames, argValues));
        StrategyInfo strategyInfo = StrategyExpressEnum.of(racContext.getStrategyRdo().getType()).getParser()
                .parse(racContext.getStrategyRdo().getExpression());
        boolean hasAuth = DataAccessStrategyHandlerBuilder.instance(strategyInfo.getType())
                .hasAuth(strategyInfo, racContext);
        if (!hasAuth) {
            throw new BaseException(ResponseCodeEnum.NO_AUTH.getValue(), ResponseCodeEnum.NO_AUTH.getText());
        }
        Object result = pjp.proceed();
        return result;
    }
}