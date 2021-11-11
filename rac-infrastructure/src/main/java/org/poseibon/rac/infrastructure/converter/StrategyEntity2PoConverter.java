package org.poseibon.rac.infrastructure.converter;

import org.poseibon.rac.domain.entity.StrategyEntity;
import org.poseibon.rac.infrastructure.po.StrategyPo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Entity2PoConverter;

/**
 * 访问策略po-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface StrategyEntity2PoConverter extends Entity2PoConverter<StrategyEntity, StrategyPo> {
    /**
     * 实例
     */
    StrategyEntity2PoConverter INSTANCE = Mappers.getMapper(StrategyEntity2PoConverter.class);
}
