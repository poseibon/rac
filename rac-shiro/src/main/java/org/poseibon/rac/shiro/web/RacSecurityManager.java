package org.poseibon.rac.shiro.web;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;


/**
 * 权限管理，配置主要是Realm的管理认证
 *
 * @author qingchuan
 * @date 2020/12/9
 */
public class RacSecurityManager extends DefaultWebSecurityManager {
    /**
     * Rac 权限管理
     *
     * @param realm          realm
     * @param cacheManager   缓存管理器
     */
    public RacSecurityManager(AuthorizingRealm realm, SessionManager sessionManager, CacheManager cacheManager) {
        this.setRealm(realm);
        this.setSessionManager(sessionManager);
        this.setCacheManager(cacheManager);
    }
}
