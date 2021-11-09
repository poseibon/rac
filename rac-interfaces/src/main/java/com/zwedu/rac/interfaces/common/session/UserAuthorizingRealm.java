package com.zwedu.rac.interfaces.common.session;

import com.zwedu.rac.sdk.provider.UserProvider;
import com.zwedu.rac.sdk.rpo.user.UserSimpleRpo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;


@Slf4j
public class UserAuthorizingRealm extends AuthorizingRealm {

    private UserProvider userProvider;

    public UserAuthorizingRealm(UserProvider userProvider) {
        this.userProvider = userProvider;
    }

    /**
     * 授权验证，获取授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("call doGetAuthorizationInfo start...");
        String user = (String) principalCollection.getPrimaryPrincipal();
        // 权限Set集合
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
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
        UserSimpleRpo user = userProvider.queryByEnName(token.getUsername());
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