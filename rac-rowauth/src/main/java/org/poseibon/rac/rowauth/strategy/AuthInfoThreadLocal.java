package org.poseibon.rac.rowauth.strategy;


import org.poseibon.rac.rowauth.strategy.vo.AuthInfo;

/**
 * 有权限的业务线ID列表 threadLocal
 *
 * @author qingchuan
 * @date 2020/12/25
 */
public interface AuthInfoThreadLocal {
    /**
     * 有权限的业务线ID列表
     */
    ThreadLocal<AuthInfo> AUTH_INFO = new ThreadLocal<>();
}
