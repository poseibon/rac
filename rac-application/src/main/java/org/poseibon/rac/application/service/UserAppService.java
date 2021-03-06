package org.poseibon.rac.application.service;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.poseibon.common.utils.Collections2;
import org.poseibon.common.validator.ParamAssert;
import org.poseibon.rac.application.converter.*;
import org.poseibon.rac.domain.common.constant.SystemConstant;
import org.poseibon.rac.domain.common.enums.ExtPropertyTypeEnum;
import org.poseibon.rac.domain.entity.DictionaryNodeEntity;
import org.poseibon.rac.domain.entity.ExtDataEntity;
import org.poseibon.rac.domain.entity.ExtPropertyEntity;
import org.poseibon.rac.domain.service.DictionaryNodeDomainService;
import org.poseibon.rac.domain.service.ExtPropertyDomainService;
import org.poseibon.rac.domain.service.UserDomainService;
import org.poseibon.rac.rowauth.annotation.ReadAuth;
import org.poseibon.rac.rowauth.annotation.WriteAuth;
import org.poseibon.rac.sdk.rdo.base.PaginationRdo;
import org.poseibon.rac.sdk.rdo.base.PaginationRpo;
import org.poseibon.rac.sdk.rdo.dimension.DimensionNodeSimpleRdo;
import org.poseibon.rac.sdk.rdo.ext.ExtDataComplexRdo;
import org.poseibon.rac.sdk.rdo.user.UserComplexRdo;
import org.poseibon.rac.sdk.rpo.ext.ExtDataSimpleRpo;
import org.poseibon.rac.sdk.rpo.user.UserPermitRpo;
import org.poseibon.rac.sdk.rpo.user.UserSimpleRpo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户应用层服务
 *
 * @author qingchuan
 * @date 2020/12/10
 */
@Service
public class UserAppService {
    @Resource
    private UserDomainService userDomainService;
    @Resource
    private ExtPropertyDomainService extPropertyDomainService;
    @Resource
    private DictionaryNodeDomainService dictionaryNodeDomainService;

    /**
     * 查询用户列表数据
     *
     * @param record 分页查询参数
     * @return 用户列表数据
     */
    @ReadAuth
    public PaginationRdo<UserComplexRdo> listPage(Long currentLoginId, PaginationRpo record) {
        return UserEntity2ComplexRdoConverter.INSTANCE.toPaginationRdo(userDomainService
                .listPage(currentLoginId, record.getPageNo(), record.getPageSize(),
                        record.getBizLineId(), record.getSearchVal()));
    }

    /**
     * 查找用户授权的角色ID
     *
     * @param record        查询用户角色参数
     */
    @ReadAuth
    public List<Long> listUserRoleId(UserPermitRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.allNotNull(record);
        return userDomainService.listUserRoleId(record.getUserId());
    }

    /**
     * 查找用户授权的维度节点
     *
     * @param record        查询用户授权维度节点参数
     */
    @ReadAuth
    public List<DimensionNodeSimpleRdo> listUserDimensionNodes(UserPermitRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.allNotNull(record);
        return DimensionNodeEntity2SimpleRdoConverter.INSTANCE.toRdoList(userDomainService
                .listUserDimensionNodes(record.getUserId()));
    }


    /**
     * 查找用户授权的扩展属性
     *
     * @param record        查询用户授权维度节点参数
     * @return
     */
    @ReadAuth
    public List<ExtDataComplexRdo> listUserExtProperty(UserPermitRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.allNotNull(record);
        List<ExtDataEntity> extDataEntityList = extPropertyDomainService
                .listExtData(record.getBizLineId(), SystemConstant.ENTITY_USER, record.getUserId());
        if (CollectionUtils.isEmpty(extDataEntityList)) {
            return Lists.newArrayList();
        }
        // 获取扩展属性ID
        List<Long> extPropertyIds = extDataEntityList.stream()
                .map(input -> input.getExtPropertyId()).collect(Collectors.toList());
        List<ExtPropertyEntity> extPropertyEntityList = extPropertyDomainService.listByIds(extPropertyIds);
        if (CollectionUtils.isEmpty(extPropertyEntityList)) {
            return Lists.newArrayList();
        }
        Map<Long, ExtPropertyEntity> extPropertyMap = Collections2.toMap(extPropertyEntityList, input -> input.getId(),
                input -> input);
        Set<Long> dictionaryIds = extPropertyEntityList.stream()
                .filter(input -> input.getType().equals(ExtPropertyTypeEnum.ENUM.getValue()))
                .map(input -> input.getDictionaryId())
                .collect(Collectors.toSet());
        Map<Long, Map<Integer, DictionaryNodeEntity>> dictionaryNodeMap =
                dictionaryNodeDomainService.listByDictionaryIds(dictionaryIds);
        return ExtDataEntity2ComplexRdoConverter.INSTANCE.toDtoList(extDataEntityList, extPropertyMap,
                dictionaryNodeMap);
    }

    /**
     * 创建用户
     *
     * @param currentLoginId 登录用户ID
     * @param record        用户实体
     */
    @WriteAuth
    public void create(Long currentLoginId, UserSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        userDomainService.create(currentLoginId, UserSimpleRpo2EntityConverter.INSTANCE.toEntity(record));
    }

    /**
     * 添加扩展属性
     *
     * @param currentLoginId 登录用户ID
     * @param record        记录数据
     */
    @WriteAuth
    public void addExtProperty(Long currentLoginId, ExtDataSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        extPropertyDomainService.addExtProperty(currentLoginId, ExtDataSimpleRpo2EntityConverter.INSTANCE.toEntity(record));
    }

    /**
     * 删除扩展属性
     *
     * @param record 记录数据
     */
    @WriteAuth
    public void dropExtProperty(ExtDataSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        extPropertyDomainService.dropExtProperty(ExtDataSimpleRpo2EntityConverter.INSTANCE.toEntity(record));
    }

    /**
     * 给用户授权
     *
     * @param currentLoginId 登录用户ID
     * @param record        用户权限记录
     */
    @WriteAuth
    public void grantRoles(Long currentLoginId, UserPermitRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.allNotNull(currentLoginId, record);
        userDomainService.bindRoles(currentLoginId, record.getUserId(), record.getRoleIds());
    }


    /**
     * 给用户授权
     *
     * @param currentLoginId 登录用户ID
     * @param record        用户权限记录
     */
    @WriteAuth
    public void grantDimensionNodes(Long currentLoginId, UserPermitRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.allNotNull(currentLoginId, record);
        userDomainService.bindDimensionNodes(currentLoginId, record.getUserId(), record.getDimensionNodeIds());
    }

    /**
     * 更新用户
     *
     * @param currentLoginId 登录用户ID
     * @param record        用户实体
     */
    @WriteAuth
    public void edit(Long currentLoginId, UserSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        userDomainService.edit(currentLoginId, UserSimpleRpo2EntityConverter.INSTANCE.toEntity(record));
    }


    /**
     * 删除用户
     *
     * @param currentLoginId 登录用户ID
     * @param record        记录数据
     */
    @WriteAuth
    public void delete(Long currentLoginId, UserSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        userDomainService.delete(currentLoginId, record.getId());
    }
}
