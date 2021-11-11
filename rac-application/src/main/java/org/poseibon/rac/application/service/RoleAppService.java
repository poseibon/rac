package org.poseibon.rac.application.service;

import org.poseibon.rac.application.converter.FuncEntity2ComplexRdoConverter;
import org.poseibon.rac.application.converter.RoleEntity2ComplexRdoConverter;
import org.poseibon.rac.application.converter.RoleEntity2SimpleRdoConverter;
import org.poseibon.rac.application.converter.RoleSimpleRpo2EntityConverter;
import org.poseibon.rac.domain.entity.FuncEntity;
import org.poseibon.rac.domain.entity.RoleEntity;
import org.poseibon.rac.domain.entity.StrategyEntity;
import org.poseibon.rac.domain.service.FuncDomainService;
import org.poseibon.rac.domain.service.RoleDomainService;
import org.poseibon.rac.domain.service.StrategyDomainService;
import org.poseibon.rac.rowauth.annotation.WriteAuth;
import org.poseibon.rac.sdk.rdo.base.PaginationRdo;
import org.poseibon.rac.sdk.rdo.base.PaginationRpo;
import org.poseibon.rac.sdk.rdo.role.FuncStrategyComplexRdo;
import org.poseibon.rac.sdk.rdo.role.RoleSimpleRdo;
import org.poseibon.rac.sdk.rpo.role.RoleComplexRpo;
import org.poseibon.rac.sdk.rpo.role.RoleSimpleRpo;
import org.apache.commons.lang3.tuple.Pair;
import org.poseibon.common.page.Pagination;
import org.poseibon.common.utils.Collections2;
import org.poseibon.common.validator.ParamAssert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 角色应用层服务
 *
 * @author qingchuan
 * @date 2020/12/10
 */
@Service
public class RoleAppService {
    @Resource
    private RoleDomainService roleDomainService;
    @Resource
    private FuncDomainService funcDomainService;
    @Resource
    private StrategyDomainService strategyDomainService;

    /**
     * 查询角色列表数据
     *
     * @param record 分页查询参数
     * @return 角色列表数据
     */
    public PaginationRdo<RoleComplexRpo> listPage(PaginationRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        // 查询对应的角色列表
        Pagination<RoleEntity> roleEntityList = roleDomainService
                .listPage(record.getPageNo(), record.getPageSize(),
                        record.getBizLineId(), record.getSearchVal());
        return RoleEntity2ComplexRdoConverter.INSTANCE.toPaginationRdo(roleEntityList);
    }

    /**
     * 查找授权角色列表
     *
     * @return 授权角色列表
     */
    public List<RoleSimpleRdo> listByBizLineId(RoleSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        List<RoleEntity> roleEntityList = roleDomainService.listByBizLineId(record.getBizLineId());
        return RoleEntity2SimpleRdoConverter.INSTANCE.toRdoList(roleEntityList);
    }

    /**
     * 查找角色功能ID
     *
     * @param record 查询角色功能参数
     */
    public PaginationRdo<FuncStrategyComplexRdo> listAuth(PaginationRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.allNotNull(record);
        Pagination<Pair<Long, Long>> pagination = roleDomainService.listAuth(record.getPageNo(),
                record.getPageSize(), record.getBizLineId(), record.getId());
        Collection<Long> funcIds = Collections2.toSet(pagination.getDataList(), input -> input.getKey());
        Collection<Long> strategyIds = Collections2.toSet(pagination.getDataList(), input -> input.getValue());
        Map<Long, FuncEntity> funcMap = funcDomainService.listByIds(record.getBizLineId(), funcIds);
        Map<Long, StrategyEntity> strategyMap = strategyDomainService.listByIds(record.getBizLineId(), strategyIds);
        return FuncEntity2ComplexRdoConverter.INSTANCE.toPaginationRdo(pagination, funcMap, strategyMap);
    }


    /**
     * 已绑定菜单ID
     *
     * @param record 记录
     * @return 列表
     */
    public List<Long> listBindMenuId(RoleSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        return roleDomainService.listBindMenuId(record.getBizLineId(), record.getRoleId());
    }

    /**
     * 创建角色
     *
     * @param currentUserId 登录用户ID
     * @param record        角色实体
     */
    @WriteAuth
    public void create(Long currentUserId, RoleSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        roleDomainService.create(currentUserId, RoleSimpleRpo2EntityConverter.INSTANCE.toEntity(record));
    }


    /**
     * 绑定角色功能
     *
     * @param currentUserId 登录用户ID
     * @param record        角色功能记录
     */
    @WriteAuth
    public void bindAuth(Long currentUserId, RoleSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.allNotNull(currentUserId, record);
        roleDomainService.bindAuth(currentUserId, record.getBizLineId(), record.getRoleId(), record.getFuncIds(),
                record.getStrategyId());
    }

    /**
     * 绑定角色菜单
     *
     * @param currentUserId 登录用户ID
     * @param record        角色功能记录
     */
    @WriteAuth
    public void bindMenu(Long currentUserId, RoleSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.allNotNull(currentUserId, record);
        roleDomainService.bindMenu(currentUserId, record.getBizLineId(), record.getRoleId(), record.getMenuIds());
    }

    /**
     * 解绑角色功能
     *
     * @param record 角色功能记录
     */
    @WriteAuth
    public void unbindAuth(RoleSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.allNotNull(record);
        roleDomainService.unbindAuth(record.getBizLineId(), record.getRoleId(), record.getFuncIds(),
                record.getStrategyId());
    }

    /**
     * 更新角色
     *
     * @param currentUserId 登录用户ID
     * @param record        角色实体
     */
    @WriteAuth
    public void edit(Long currentUserId, RoleSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        roleDomainService.edit(currentUserId, RoleSimpleRpo2EntityConverter.INSTANCE.toEntity(record));
    }


    /**
     * 删除角色
     *
     * @param currentUserId 登录用户ID
     * @param record        记录数据
     */
    @WriteAuth
    public void delete(Long currentUserId, RoleSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        roleDomainService.delete(currentUserId, record.getBizLineId(), record.getId());
    }
}
