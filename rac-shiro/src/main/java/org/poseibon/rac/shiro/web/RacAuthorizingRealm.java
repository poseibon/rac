package org.poseibon.rac.shiro.web;

import org.apache.shiro.cache.CacheManager;
import org.poseibon.rac.sdk.provider.AuthProvider;
import org.poseibon.rac.sdk.rdo.user.UserSimpleRdo;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * shiro配置
 *
 * @author qingchuan
 * @date 2020/12/12
 */
public class RacAuthorizingRealm extends AuthorizingRealm {
    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(RacAuthorizingRealm.class);
    /**
     * 业务线ID
     */
    private Long bizLineId;
    /**
     * 权限接口
     */
    private AuthProvider authProvider;

    /**
     * 用户自定义Realm
     *
     * @param authProvider 授权服务
     */
    public RacAuthorizingRealm(AuthProvider authProvider, Long bizLineId) {
        this.authProvider = authProvider;
        this.bizLineId = bizLineId;
        this.setCachingEnabled(true);
        this.setAuthenticationCachingEnabled(true);
        this.setAuthenticationCacheName("Shiro-AuthenticationCache-" + bizLineId);
        this.setAuthorizationCachingEnabled(true);
        this.setAuthorizationCacheName("Shiro-AuthorizationCache" + bizLineId);
    }

    /**
     * 授权验证，获取授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("call doGetAuthorizationInfo start...");
        // 权限Set集合
        return new SimpleAuthorizationInfo();
    }

    /**
     * 登录验证，获取身份信息
     *
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        log.info("call doGetAuthenticationInfo start...");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        if (token.getPassword() == null || token.getUsername() == null) {
            return null;
        }
        // 获取用户
        UserSimpleRdo user = authProvider.querySimpleUser(token.getUsername());
        if (user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }
        // 验证密码 @TODO 加盐验证
        if (!user.getPassword().equals(new String(token.getPassword()))) {
            throw new UnknownAccountException("账号或密码不正确");
        }
        //
        return new SimpleAuthenticationInfo(user.getEnName(), user.getPassword(), user.getCnName());
    }
}