package org.poseibon.rac.interfaces.rpc;

import org.poseibon.rac.application.service.AuthAppService;
import org.poseibon.rac.sdk.provider.AuthProvider;
import org.poseibon.rac.sdk.rdo.dimension.DimensionNodeRdo;
import org.poseibon.rac.sdk.rdo.ext.ExtPropertyRdo;
import org.poseibon.rac.sdk.rdo.menu.MenuRdo;
import org.poseibon.rac.sdk.rdo.role.FuncRdo;
import org.poseibon.rac.sdk.rdo.user.UserRdo;
import org.poseibon.rac.sdk.rdo.user.UserSimpleRdo;
import org.poseibon.rac.sdk.rpo.dimension.DimensionAuthRpo;
import org.poseibon.rac.sdk.rpo.auth.FuncAuthRpo;
import org.poseibon.rac.sdk.rpo.menu.MenuRpo;
import org.poseibon.rac.sdk.rpo.ext.UserExtPropertyRpo;
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
