package com.zwedu.rac.sdk.provider;

import com.zwedu.rac.sdk.rpo.user.UserSimpleRpo;

/**
 * 权限服务接口类
 *
 * @author qingchuan
 * @date 2021/11/08
 */
public interface UserProvider {
    /**
     * 跟进用户名查询用户对象
     *
     * @param username 用户名
     * @return 简单用户对象
     */
    UserSimpleRpo queryByEnName(String username);
}
