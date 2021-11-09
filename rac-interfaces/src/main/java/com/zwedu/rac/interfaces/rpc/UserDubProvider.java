package com.zwedu.rac.interfaces.rpc;

import com.zwedu.rac.application.service.UserAppService;
import com.zwedu.rac.sdk.provider.UserProvider;
import com.zwedu.rac.sdk.rpo.user.UserSimpleRpo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 功能权限dubbo实现
 *
 * @author qingchuan
 * @date 2020/12/11
 */
@Service
@DubboService
public class UserDubProvider implements UserProvider {
    @Resource
    private UserAppService userAppService;


    @Override
    public UserSimpleRpo queryByEnName(String username) {
        return userAppService.queryByEnName(username);
    }
}
