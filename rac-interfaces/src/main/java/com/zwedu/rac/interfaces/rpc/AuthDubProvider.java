package com.zwedu.rac.interfaces.rpc;

import com.zwedu.rac.application.service.AuthAppService;
import com.zwedu.rac.sdk.provider.AuthProvider;
import com.zwedu.rac.sdk.rdo.*;
import com.zwedu.rac.sdk.rdo.user.UserSimpleRdo;
import com.zwedu.rac.sdk.rpo.dimension.DimensionAuthRpo;
import com.zwedu.rac.sdk.rpo.auth.FuncAuthRpo;
import com.zwedu.rac.sdk.rpo.menu.MenuRpo;
import com.zwedu.rac.sdk.rpo.ext.UserExtPropertyRpo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能权限dubbo实现
 *
 * @author qingchuan
 * @date 2020/12/11
 */
@Service
@DubboService
public class AuthDubProvider implements AuthProvider {
    @Resource
    private AuthAppService authAppService;

    @Override
    public List<FuncRdo> listFuncAuth(FuncAuthRpo rpo) {
        return authAppService.listFuncAuth(rpo);
    }

    @Override
    public List<MenuRdo> listMenu(MenuRpo rpo) {
        return authAppService.listMenu(rpo);
    }

    @Override
    public List<DimensionNodeRdo> listDimensionNodeByUser(DimensionAuthRpo rpo) {
        return authAppService.listDimensionNodeByUser(rpo);
    }

    @Override
    public List<DimensionNodeRdo> listDimensionNodeWithChildByUser(DimensionAuthRpo rpo) {
        return authAppService.listDimensionNodeWithChildByUser(rpo);
    }

    @Override
    public List<ExtPropertyRdo> listExtPropertyByUser(UserExtPropertyRpo rpo) {
        return authAppService.listExtPropertyByUser(rpo);
    }

    @Override
    public UserRdo queryUser(Long bizLineId, String userName) {
        return authAppService.queryUser(bizLineId, userName);
    }

    @Override
    public UserSimpleRdo querySimpleUser(String userName) {
        return authAppService.querySimpleUser(userName);
    }


}
