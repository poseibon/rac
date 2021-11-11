package org.poseibon.rac.infrastructure.converter;

import org.poseibon.rac.domain.entity.StrategyEntity;
import org.poseibon.rac.infrastructure.po.StrategyPo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Po2EntityConverter;

/**
 * 访问策略po-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface StrategyPo2EntityConverter extends Po2EntityConverter<StrategyPo, StrategyEntity> {
    /**
     * 实例
     */
    StrategyPo2EntityConverter INSTANCE = Mappers.getMapper(StrategyPo2EntityConverter.class);
}
