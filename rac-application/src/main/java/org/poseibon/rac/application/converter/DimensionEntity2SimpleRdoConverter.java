package org.poseibon.rac.application.converter;

import org.poseibon.rac.domain.entity.DimensionEntity;
import org.poseibon.rac.sdk.rdo.dimension.DimensionSimpleRdo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 维度entity-rdo转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface DimensionEntity2SimpleRdoConverter extends Entity2RdoExtConverter
        <DimensionEntity, DimensionSimpleRdo> {
    /**
     * 实例
     */
    DimensionEntity2SimpleRdoConverter INSTANCE = Mappers.getMapper(DimensionEntity2SimpleRdoConverter.class);
}
