package org.poseibon.rac.rowauth.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.poseibon.common.utils.Args2;
import org.poseibon.rac.rowauth.enums.StrategyExpressEnum;
import org.poseibon.rac.rowauth.strategy.vo.StrategyInfo;
import org.poseibon.rac.sdk.threadlocal.RacContextThreadLocal;
import org.poseibon.rac.sdk.vo.RacContext;

/**
 * 自定义拦截器-方法拦截器，基于spring aop
 *
 * @author qingchuan
 * @date 2020/12/9
 */
public interface AbstractAuthInterceptor {

    default AuthContext getAuthContext(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Object[] argValues = pjp.getArgs();
        String[] argNames = methodSignature.getParameterNames();
        RacContext racContext = RacContextThreadLocal.CURRENT_RAC_CONTEXT.get()
                .paramMap(Args2.parseArgs(argNames, argValues));
        StrategyInfo strategyInfo = StrategyExpressEnum.of(racContext.getStrategyRdo().getType()).getParser()
                .parse(racContext.getStrategyRdo().getExpression());
        return new AuthContext(racContext, strategyInfo);
    }

    /**
     * 权限参数上下文
     *
     * @author qingchuan
     * @date 2020/12/9
     */
    class AuthContext {
        private RacContext racContext;
        private StrategyInfo strategyInfo;

        public AuthContext(RacContext racContext, StrategyInfo strategyInfo) {
            this.racContext = racContext;
            this.strategyInfo = strategyInfo;
        }

        public RacContext getRacContext() {
            return racContext;
        }

        public StrategyInfo getStrategyInfo() {
            return strategyInfo;
        }
    }
}
