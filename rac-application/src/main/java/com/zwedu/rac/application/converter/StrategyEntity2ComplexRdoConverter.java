package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rpo.strategy.StrategyComplexRpo;
import com.zwedu.rac.domain.entity.StrategyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Entity2RdoConverter;

/**
 * 访问策略实体entity-dto转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface StrategyEntity2ComplexRdoConverter extends
        Entity2RdoExtConverter<StrategyEntity, StrategyComplexRpo> {
    /**
     * 实例
     */
    StrategyEntity2ComplexRdoConverter INSTANCE = Mappers.getMapper(StrategyEntity2ComplexRdoConverter.class);
}
