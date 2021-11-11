package org.poseibon.rac.application.converter;

import org.poseibon.rac.sdk.rdo.strategy.StrategyComplexRdo;
import org.poseibon.rac.domain.entity.StrategyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 访问策略实体entity-rdo转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface StrategyEntity2ComplexRdoConverter extends
        Entity2RdoExtConverter<StrategyEntity, StrategyComplexRdo> {
    /**
     * 实例
     */
    StrategyEntity2ComplexRdoConverter INSTANCE = Mappers.getMapper(StrategyEntity2ComplexRdoConverter.class);
}
