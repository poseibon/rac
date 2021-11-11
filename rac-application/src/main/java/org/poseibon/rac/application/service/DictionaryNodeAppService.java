package org.poseibon.rac.application.service;

import com.google.common.collect.Lists;
import org.poseibon.rac.application.converter.DictionaryNodeEntity2ComplexRdoConverter;
import org.poseibon.rac.application.converter.DictionaryNodeSimpleRpo2EntityConverter;
import org.poseibon.rac.application.converter.ExtDataEntity2ComplexRdoConverter;
import org.poseibon.rac.application.converter.ExtDataSimpleRpo2EntityConverter;
import org.poseibon.rac.domain.common.enums.EntityPrefixEnum;
import org.poseibon.rac.domain.common.enums.ExtPropertyTypeEnum;
import org.poseibon.rac.domain.entity.DictionaryEntity;
import org.poseibon.rac.domain.entity.DictionaryNodeEntity;
import org.poseibon.rac.domain.entity.ExtDataEntity;
import org.poseibon.rac.domain.entity.ExtPropertyEntity;
import org.poseibon.rac.domain.service.DictionaryDomainService;
import org.poseibon.rac.domain.service.DictionaryNodeDomainService;
import org.poseibon.rac.domain.service.ExtPropertyDomainService;
import org.poseibon.rac.rowauth.annotation.ReadAuth;
import org.poseibon.rac.rowauth.annotation.WriteAuth;
import org.poseibon.rac.sdk.rdo.dictionary.DictionaryNodeComplexRdo;
import org.poseibon.rac.sdk.rpo.dictionary.DictionaryNodeSimpleRpo;
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
 * 字典节点应用层服务
 *
 * @author qingchuan
 * @date 2020/12/10
 */
@Service
public class DictionaryNodeAppService {
    @Resource
    private DictionaryDomainService dictionaryDomainService;
    @Resource
    private DictionaryNodeDomainService dictionaryNodeDomainService;
    @Resource
    private ExtPropertyDomainService extPropertyDomainService;


    /**
     * 查询授权的字典节点列表
     *
     * @param record 参数
     * @return 字典节点列表数据
     */
    @ReadAuth
    public List<DictionaryNodeComplexRdo> listByParentId(DictionaryNodeSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        return DictionaryNodeEntity2ComplexRdoConverter.INSTANCE.toRdoList(dictionaryNodeDomainService
                .listByParentId(record.getBizLineId(), record.getDictionaryId(), record.getParentId()));
    }


    /**
     * 查询业务线对应的维度节点
     *
     * @param record 参数
     * @return 维度节点列表
     */
    @ReadAuth
    public List<DictionaryNodeComplexRdo> listByDictionaryId(DictionaryNodeSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        List<DictionaryNodeEntity> dictionaryEntityList = dictionaryNodeDomainService
                .listByDictionaryId(record.getBizLineId(), record.getDictionaryId(), record.getSearchVal());
        List<DictionaryNodeComplexRdo> dtoList = DictionaryNodeEntity2ComplexRdoConverter.INSTANCE
                .toRdoList(dictionaryEntityList);
        return TreeBuilder.buildTree(dtoList);
    }

    /**
     * 创建字典节点
     *
     * @param currentUserId 登录用户ID
     * @param record        字典节点实体
     */
    @WriteAuth
    public void create(Long currentUserId, DictionaryNodeSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        dictionaryNodeDomainService.create(currentUserId,
                DictionaryNodeSimpleRpo2EntityConverter.INSTANCE.toEntity(record));
    }

    /**
     * 更新字典节点
     *
     * @param currentUserId 登录用户ID
     * @param record        字典节点实体
     */
    @WriteAuth
    public void edit(Long currentUserId, DictionaryNodeSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        dictionaryNodeDomainService.edit(currentUserId,
                DictionaryNodeSimpleRpo2EntityConverter.INSTANCE.toEntity(record));
    }


    /**
     * 删除字典节点
     *
     * @param currentUserId 登录用户ID
     * @param record        记录数据
     */
    @WriteAuth
    public void delete(Long currentUserId, DictionaryNodeSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        dictionaryNodeDomainService.delete(currentUserId, record.getBizLineId(), record.getDictionaryId(), record.getId());
    }

    /**
     * 查找扩展属性
     *
     * @param record 查询用户授权维度节点参数
     * @return
     */
    public List<ExtDataComplexRdo> listExtProperty(DictionaryNodeSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.allNotNull(record);
        DictionaryEntity dictionaryEntity = dictionaryDomainService.queryById(record.getBizLineId(),
                record.getDictionaryId());
        if (dictionaryEntity == null) {
            return Lists.newArrayList();
        }
        List<ExtDataEntity> extDataEntityList = extPropertyDomainService
                .listExtProperty(record.getBizLineId(), EntityPrefixEnum.DICTIONARY.join(dictionaryEntity.getEnName()),
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
