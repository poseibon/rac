package org.poseibon.rac.infrastructure.repository;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import org.poseibon.rac.domain.entity.FuncEntity;
import org.poseibon.rac.domain.entity.StrategyEntity;
import org.poseibon.rac.domain.repository.FuncRepository;
import org.poseibon.rac.infrastructure.converter.FuncEntity2PoConverter;
import org.poseibon.rac.infrastructure.converter.FuncPo2EntityConverter;
import org.poseibon.rac.infrastructure.converter.StrategyPo2EntityConverter;
import org.poseibon.rac.infrastructure.mapper.FuncMapper;
import org.poseibon.rac.infrastructure.mapper.StrategyMapper;
import org.poseibon.rac.infrastructure.po.FuncExtPo;
import org.poseibon.rac.infrastructure.po.FuncPo;
import org.poseibon.rac.infrastructure.po.IdNumPo;
import org.poseibon.rac.infrastructure.po.StrategyPo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.poseibon.common.utils.Collections2;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 功能存储类
 *
 * @author qingchuan
 * @date 2020/12/10
 */
@Repository
public class FuncMybatisRepository implements FuncRepository {
    @Resource
    private FuncMapper funcMapper;
    @Resource
    private StrategyMapper strategyMapper;

    @Override
    public List<FuncEntity> listByRoleIds(Long bizLineId, Collection<Long> roleIds) {
        List<FuncExtPo> poList = funcMapper.listByRoleIds(bizLineId, roleIds);
        if (CollectionUtils.isEmpty(poList)) {
            return Lists.newArrayList();
        }
        List<StrategyPo> strategyPoList = strategyMapper
                .listByIds(bizLineId, Collections2.toSet(poList, input -> input.getStrategyId()));
        List<StrategyEntity> strategyEntityList = StrategyPo2EntityConverter.INSTANCE.toEntityList(strategyPoList);
        if (CollectionUtils.isEmpty(strategyEntityList)) {
            return Lists.newArrayList();
        }
        Map<Long, StrategyEntity> strategyEntityMap = Collections2.toMap(strategyEntityList,
                input -> input.getId(), input -> input);
        return FuncPo2EntityConverter.INSTANCE.toComplexEntityList(poList, strategyEntityMap);
    }

    @Override
    public List<FuncEntity> listByParentId(Long bizLineId, Long parentId) {
        List<FuncPo> poList = funcMapper.listByParentId(bizLineId, parentId);
        if (CollectionUtils.isEmpty(poList)) {
            return Lists.newArrayList();
        }
        Set<Long> idSet = poList.stream().map(input -> input.getId()).collect(Collectors.toSet());
        List<IdNumPo> childCountList = funcMapper.countChildByIds(bizLineId, idSet);
        Map<Long, Long> id2ChildCountMap = Collections2.toMap(childCountList, input -> input.getId(),
                input -> input.getChildCount());
        return FuncPo2EntityConverter.INSTANCE.toEntityList(poList, id2ChildCountMap);
    }

    @Override
    public List<FuncEntity> listByBizLineId(Long bizLineId, String searchVal) {
        List<FuncPo> poList = funcMapper.listByBizLineId(bizLineId, searchVal);
        List<FuncEntity> retList = FuncPo2EntityConverter.INSTANCE.toEntityList(poList);
        if (StringUtils.isNotEmpty(searchVal) && CollectionUtils.isNotEmpty(retList)) {
            // 如果检索值不为空，并且查询出结果，则按照节点path查找子节点
            List<FuncPo> childList = funcMapper.listByParentPaths(bizLineId,
                    Collections2.toList(retList, input -> input.getPath()));
            retList.addAll(FuncPo2EntityConverter.INSTANCE.toEntityList(childList));
        }
        return retList;
    }

    @Override
    public List<FuncEntity> listByBizLineId(Long bizLineId) {
        List<FuncPo> poList = funcMapper.listBizLineAll(bizLineId);
        List<FuncEntity> retList = FuncPo2EntityConverter.INSTANCE.toEntityList(poList);
        return retList;
    }

    @Override
    public FuncEntity queryById(Long id) {
        FuncPo record = funcMapper.selectByPrimaryKey(id);
        return FuncPo2EntityConverter.INSTANCE.toEntity(record);
    }

    @Override
    public FuncEntity queryById(Long bizLineId, Long id) {
        FuncPo record = funcMapper.queryById(bizLineId, id);
        return FuncPo2EntityConverter.INSTANCE.toEntity(record);
    }

    @Override
    public Boolean hasSameEnName(Long bizLineId, String enName, Long id) {
        return funcMapper.queryByEnName(bizLineId, enName, id) != null;
    }

    @Override
    public Boolean hasSameCnName(Long bizLineId, String cnName, Long id) {
        return funcMapper.queryByCnName(bizLineId, cnName, id) != null;
    }

    @Override
    public void insert(FuncEntity record) {
        funcMapper.insertSelective(FuncEntity2PoConverter.INSTANCE.toPo(record));
    }

    /**
     * 更新功能
     *
     * @param record 功能实体
     */
    @Override
    public void edit(FuncEntity record) {
        funcMapper.updateByPrimaryKeySelective(FuncEntity2PoConverter.INSTANCE.toPo(record));
    }


    /**
     * 删除功能
     *
     * @param record 功能实体
     */
    @Override
    public void delete(FuncEntity record) {
        funcMapper.updateByPrimaryKeySelective(FuncEntity2PoConverter.INSTANCE.toPo(record));
    }

    @Override
    public List<FuncEntity> listByIds(Long bizLineId, Collection<Long> funcIds) {
        return FuncPo2EntityConverter.INSTANCE.toEntityList(funcMapper
                .listByIds(bizLineId, ImmutableSet.copyOf(funcIds)));
    }

    @Override
    public List<FuncEntity> listByParentPaths(Long bizLineId, Collection<String> parentPaths) {
        return FuncPo2EntityConverter.INSTANCE.toEntityList(funcMapper
                .listByParentPaths(bizLineId, parentPaths));
    }

    @Override
    public void changeNodeWithNewPath(Long bizLineId, String newParentPath, String oldParentPath, Set<Long> ids) {
        funcMapper.changeNodeWithNewPath(bizLineId, newParentPath, oldParentPath, ids);
    }
}
