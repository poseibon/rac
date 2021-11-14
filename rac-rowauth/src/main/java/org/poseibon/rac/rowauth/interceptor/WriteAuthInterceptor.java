package org.poseibon.rac.rowauth.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.poseibon.common.enums.ResponseCodeEnum;
import org.poseibon.common.exception.BaseException;
import org.poseibon.rac.rowauth.strategy.AuthInfoThreadLocal;
import org.poseibon.rac.rowauth.strategy.DataAccessStrategyHandlerBuilder;
import org.poseibon.rac.rowauth.strategy.vo.AuthInfo;
import org.springframework.stereotype.Component;

/**
 * 自定义拦截器-方法拦截器，基于spring aop
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Component
@Aspect
public class WriteAuthInterceptor implements AbstractAuthInterceptor {

    @Around("@annotation(org.poseibon.rac.rowauth.annotation.WriteAuth)")
    public Object handle(ProceedingJoinPoint pjp) throws Throwable {
        AuthContext authContext = getAuthContext(pjp);
        AuthInfo authInfo = DataAccessStrategyHandlerBuilder.instance(authContext.getStrategyInfo().getType())
                .getAuthInfo(authContext.getStrategyInfo(), authContext.getRacContext());
        boolean hasAuth = DataAccessStrategyHandlerBuilder.instance(authContext.getStrategyInfo().getType())
                .hasAuth(authInfo, authContext.getStrategyInfo(), authContext.getRacContext());
        if (!hasAuth) {
            throw new BaseException(ResponseCodeEnum.NO_AUTH.getValue(), ResponseCodeEnum.NO_AUTH.getText());
        }
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