package org.poseibon.rac.application.converter;

import com.google.common.collect.Lists;
import org.poseibon.rac.sdk.rdo.base.PaginationRdo;
import org.poseibon.rac.sdk.rdo.role.FuncStrategyComplexRdo;
import org.poseibon.rac.sdk.rdo.func.FuncComplexRdo;
import org.poseibon.common.page.Pagination;
import org.poseibon.rac.domain.entity.FuncEntity;
import org.poseibon.rac.domain.entity.StrategyEntity;
import org.apache.commons.lang3.tuple.Pair;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 功能entity-rdo转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface FuncEntity2ComplexRdoConverter extends
        Entity2RdoExtConverter<FuncEntity, FuncComplexRdo> {
    /**
     * 实例
     */
    FuncEntity2ComplexRdoConverter INSTANCE = Mappers.getMapper(FuncEntity2ComplexRdoConverter.class);

    /**
     * 转化分页
     *
     * @param pagination  分页对象
     * @param funcMap     功能map
     * @param strategyMap 策略map
     * @return 分页对象
     */
    @Mappings({
            @Mapping(target = "dataList",
                    expression = "java(toRdoList(pagination.getDataList(), funcMap, strategyMap))")
    })
    PaginationRdo<FuncStrategyComplexRdo> toPaginationRdo(Pagination<Pair<Long, Long>> pagination,
                                                          Map<Long, FuncEntity> funcMap,
                                                          Map<Long, StrategyEntity> strategyMap);

    /**
     * entity 转 dto
     *
     * @return dto对象
     */
    @Mappings({
            @Mapping(target = "funcId", expression = "java(record.getKey())"),
            @Mapping(target = "strategyId", expression = "java(record.getValue())"),
            @Mapping(target = "funcName",
                    expression = "java(funcMap.containsKey(record.getKey())?" +
                            "funcMap.get(record.getKey()).getCnName():\"\")"),
            @Mapping(target = "strategyName",
                    expression = "java(strategyMap.containsKey(record.getValue())?" +
                            "strategyMap.get(record.getValue()).getCnName():\"\")")
    })
    FuncStrategyComplexRdo toDto(Pair<Long, Long> record, Map<Long, FuncEntity> funcMap,
                                 Map<Long, StrategyEntity> strategyMap);

    /**
     * entityList 转 dtoList
     *
     * @param recordList 实体列表对象
     * @return dto对象
     */
    default List<FuncStrategyComplexRdo> toRdoList(List<Pair<Long, Long>> recordList, Map<Long, FuncEntity> funcMap,
                                                   Map<Long, StrategyEntity> strategyMap) {
        if (CollectionUtils.isEmpty(recordList)) {
            return Lists.newArrayList();
        }
        return recordList.stream().map(input -> toDto(input, funcMap, strategyMap)).collect(Collectors.toList());
    }
}
