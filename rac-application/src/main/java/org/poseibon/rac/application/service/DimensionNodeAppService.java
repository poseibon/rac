package org.poseibon.rac.application.service;

import com.google.common.collect.Lists;
import org.poseibon.rac.application.converter.DimensionNodeEntity2ComplexRdoConverter;
import org.poseibon.rac.application.converter.DimensionNodeSimpleRpo2EntityConverter;
import org.poseibon.rac.application.converter.ExtDataEntity2ComplexRdoConverter;
import org.poseibon.rac.application.converter.ExtDataSimpleRpo2EntityConverter;
import org.poseibon.rac.domain.entity.*;
import org.poseibon.rac.domain.common.enums.EntityPrefixEnum;
import org.poseibon.rac.domain.common.enums.ExtPropertyTypeEnum;
import org.poseibon.rac.domain.service.DictionaryNodeDomainService;
import org.poseibon.rac.domain.service.DimensionDomainService;
import org.poseibon.rac.domain.service.DimensionNodeDomainService;
import org.poseibon.rac.domain.service.ExtPropertyDomainService;
import org.poseibon.rac.rowauth.annotation.ReadAuth;
import org.poseibon.rac.rowauth.annotation.WriteAuth;
import org.poseibon.rac.sdk.rdo.dimension.DimensionNodeComplexRdo;
import org.poseibon.rac.sdk.rpo.dimension.DimensionNodeSimpleRpo;
import org.poseibon.rac.sdk.rdo.ext.ExtDataComplexRdo;
import org.poseibon.rac.sdk.rpo.ext.ExtDataSimpleRpo;
import org.apache.commons.collections4.CollectionUtils;
import org.poseibon.common.tree.TreeBuilder;
import org.poseibon.common.utils.Collections2;
import org.poseibon.common.validator.ParamAssert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 维度节点节点应用层服务
 *
 * @author qingchuan
 * @date 2020/12/10
 */
@Service
public class DimensionNodeAppService {
    @Resource
    private DimensionNodeDomainService dimensionNodeDomainService;
    @Resource
    private DimensionDomainService dimensionDomainService;
    @Resource
    private ExtPropertyDomainService extPropertyDomainService;
    @Resource
    private DictionaryNodeDomainService dictionaryNodeDomainService;


    /**
     * 查询授权的维度节点节点列表
     *
     * @return 维度节点节点列表数据
     */
    @ReadAuth
    public List<DimensionNodeComplexRdo> listByParentId(DimensionNodeSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        return DimensionNodeEntity2ComplexRdoConverter.INSTANCE.toRdoList(dimensionNodeDomainService
                .listByParentId(record.getBizLineId(), record.getDimensionId(), record.getParentId()));
    }


    /**
     * 查询菜单
     *
     * @param record 参数
     * @return 列表数据
     */
    @ReadAuth
    public List<DimensionNodeComplexRdo> listByDimensionId(DimensionNodeSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.allNotNull(record);
        // 查询对应的功能列表
        List<DimensionNodeEntity> dimensionNodeEntityList = dimensionNodeDomainService
                .listByDimensionId(record.getBizLineId(), record.getDimensionId(), record.getSearchVal());
        List<DimensionNodeComplexRdo> funcComplexDtoList = DimensionNodeEntity2ComplexRdoConverter.INSTANCE
                .toRdoList(dimensionNodeEntityList);
        return TreeBuilder.buildTree(funcComplexDtoList);
    }

    /**
     * 创建维度节点节点
     *
     * @param currentUserId 登录用户ID
     * @param record        维度节点节点实体
     */
    @WriteAuth
    public void create(Long currentUserId, DimensionNodeSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        dimensionNodeDomainService.create(currentUserId, DimensionNodeSimpleRpo2EntityConverter.INSTANCE.toEntity(record));
    }

    /**
     * 更新维度节点节点
     *
     * @param currentUserId 登录用户ID
     * @param record        维度节点节点实体
     */
    @WriteAuth
    public void edit(Long currentUserId, DimensionNodeSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        dimensionNodeDomainService.edit(currentUserId, DimensionNodeSimpleRpo2EntityConverter.INSTANCE.toEntity(record));
    }


    /**
     * 删除维度节点节点
     *
     * @param currentUserId 登录用户ID
     * @param record        记录数据
     */
    @WriteAuth
    public void delete(Long currentUserId, DimensionNodeSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        dimensionNodeDomainService.delete(currentUserId, record.getBizLineId(), record.getDimensionId(), record.getId());
    }

    /**
     * 绑定主体节点操作客体节点
     *
     * @param currentUserId 登录用户ID
     * @param record        维度节点节点实体
     */
    @WriteAuth
    public void bindObjectNode(Long currentUserId, DimensionNodeSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(currentUserId, record);
        dimensionNodeDomainService.bindObjectNode(currentUserId, record.getBizLineId(), record.getDimensionId(),
                record.getId(), record.getObjectNodeId());
    }

    /**
     * 解绑主体节点操作客体节点
     *
     * @param currentUserId 登录用户ID
     * @param record        维度节点节点实体
     */
    @WriteAuth
    public void unbindObjectNode(Long currentUserId, DimensionNodeSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(currentUserId, record);
        dimensionNodeDomainService.unbindObjectNode(currentUserId, record.getBizLineId(), record.getDimensionId(),
                record.getId(), record.getObjectNodeId());
    }

    /**
     * 查询客体节点
     *
     * @param record 记录
     * @return 客体节点
     */
    @ReadAuth
    public DimensionNodeComplexRdo queryObjectNode(DimensionNodeSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        Long objectNodeId = dimensionNodeDomainService.queryObjectNodeId(record.getBizLineId(), record.getDimensionId(),
                record.getId());
        if (objectNodeId == null) {
            return null;
        }
        return DimensionNodeEntity2ComplexRdoConverter.INSTANCE.toRdo(dimensionNodeDomainService
                .queryById(record.getBizLineId(), record.getDimensionId(), objectNodeId));
    }

    /**
     * 查找扩展属性
     *
     * @param record 查询维度节点扩展属性
     * @return 维度扩展属性列表
     */
    @ReadAuth
    public List<ExtDataComplexRdo> listExtProperty(DimensionNodeSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.allNotNull(record);
        DimensionEntity dimensionEntity = dimensionDomainService
                .queryById(record.getBizLineId(), record.getDimensionId());
        if (dimensionEntity == null) {
            return Lists.newArrayList();
        }
        List<ExtDataEntity> extDataEntityList = extPropertyDomainService
                .listExtData(record.getBizLineId(), EntityPrefixEnum.DIMENSION.join(dimensionEntity.getEnName()),
                        record.getId());
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
     * 添加扩展属性
     *
     * @param currentUserId 登录用户ID
     * @param record        记录数据
     */
    @WriteAuth
    public void addExtProperty(Long currentUserId, ExtDataSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        extPropertyDomainService.addExtProperty(currentUserId,
                ExtDataSimpleRpo2EntityConverter.INSTANCE.toEntity(record));
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
}
