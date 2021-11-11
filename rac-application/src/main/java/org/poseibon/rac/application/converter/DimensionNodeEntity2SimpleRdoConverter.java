package org.poseibon.rac.application.converter;

import org.poseibon.rac.sdk.rdo.dimension.DimensionNodeSimpleRdo;
import org.poseibon.rac.domain.entity.DimensionNodeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 维度entity-rdo转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface DimensionNodeEntity2SimpleRdoConverter extends Entity2RdoExtConverter<DimensionNodeEntity, DimensionNodeSimpleRdo> {
    /**
     * 实例
     */
    DimensionNodeEntity2SimpleRdoConverter INSTANCE = Mappers.getMapper(DimensionNodeEntity2SimpleRdoConverter.class);
}
