package org.poseibon.rac.application.service;

import org.poseibon.rac.application.converter.MenuEntity2ComplexRdoConverter;
import org.poseibon.rac.application.converter.MenuSimpleRpo2EntityConverter;
import org.poseibon.rac.domain.entity.MenuEntity;
import org.poseibon.rac.domain.service.MenuDomainService;
import org.poseibon.rac.rowauth.annotation.WriteAuth;
import org.poseibon.rac.sdk.rdo.menu.MenuComplexRdo;
import org.poseibon.rac.sdk.rpo.menu.MenuSimpleRpo;
import org.poseibon.common.tree.TreeBuilder;
import org.poseibon.common.validator.ParamAssert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单应用层服务
 *
 * @author qingchuan
 * @date 2020/12/10
 */
@Service
public class MenuAppService {
    @Resource
    private MenuDomainService menuDomainService;


    /**
     * 查询子菜单
     *
     * @param record 查询参数
     * @return 菜单列表数据
     */
    public List<MenuComplexRdo> listByParentId(MenuSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        // 查询对应的菜单列表
        List<MenuEntity> MenuEntityList = menuDomainService.
                listByParentId(record.getBizLineId(), record.getParentId());
        return MenuEntity2ComplexRdoConverter.INSTANCE.toRdoList(MenuEntityList);
    }

    /**
     * 查询菜单
     *
     * @param record 参数
     * @return 列表数据
     */
    public List<MenuComplexRdo> listByBizLineId(MenuSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.allNotNull(record);
        // 查询对应的功能列表
        List<MenuEntity> menuEntityList = menuDomainService.listByBizLineId(record.getBizLineId(),
                record.getSearchVal());
        List<MenuComplexRdo> funcComplexDtoList = MenuEntity2ComplexRdoConverter.INSTANCE.toRdoList(menuEntityList);
        return TreeBuilder.buildTree(funcComplexDtoList);
    }

    /**
     * 创建菜单
     *
     * @param currentUserId 登录用户ID
     * @param record        菜单实体
     */
    @WriteAuth
    public void create(Long currentUserId, MenuSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        menuDomainService.create(currentUserId, MenuSimpleRpo2EntityConverter.INSTANCE.toEntity(record));
    }

    /**
     * 更新菜单
     *
     * @param currentUserId 登录用户ID
     * @param record        菜单实体
     */
    @WriteAuth
    public void edit(Long currentUserId, MenuSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        menuDomainService.edit(currentUserId, MenuSimpleRpo2EntityConverter.INSTANCE.toEntity(record));
    }


    /**
     * 删除菜单
     *
     * @param currentUserId 登录用户ID
     * @param record        记录数据
     */
    @WriteAuth
    public void delete(Long currentUserId, MenuSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        menuDomainService.delete(currentUserId, record.getBizLineId(), record.getId());
    }

}
