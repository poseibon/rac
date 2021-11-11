package org.poseibon.rac.application.converter;

import org.poseibon.rac.domain.entity.StrategyEntity;
import org.poseibon.rac.sdk.rdo.strategy.StrategySimpleRdo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 访问策略实体entity-rdo转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface StrategyEntity2SimpleRdoConverter extends
        Entity2RdoExtConverter<StrategyEntity, StrategySimpleRdo> {
    /**
     * 实例
     */
    StrategyEntity2SimpleRdoConverter INSTANCE = Mappers.getMapper(StrategyEntity2SimpleRdoConverter.class);
}
