package org.poseibon.rac.rowauth.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.poseibon.common.enums.DataAccessEnum;
import org.poseibon.rac.rowauth.strategy.AuthInfoThreadLocal;
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
public class NoneAuthInterceptor implements AbstractAuthInterceptor{

    @Around("@annotation(org.poseibon.rac.rowauth.annotation.NoneAuth)")
    public Object handle(ProceedingJoinPoint pjp) throws Throwable {
        AuthInfo authInfo = AuthInfo.of().dataAccess(DataAccessEnum.ALL);
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