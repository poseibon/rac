package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rpo.strategy.StrategySimpleRpo;
import com.zwedu.rac.domain.entity.StrategyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Rpo2EntityConverter;

/**
 * 访问策略实体entity-dto转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface StrategySimpleRpo2EntityConverter extends
        Rpo2EntityConverter<StrategySimpleRpo, StrategyEntity> {
    /**
     * 实例
     */
    StrategySimpleRpo2EntityConverter INSTANCE = Mappers.getMapper(StrategySimpleRpo2EntityConverter.class);
}
