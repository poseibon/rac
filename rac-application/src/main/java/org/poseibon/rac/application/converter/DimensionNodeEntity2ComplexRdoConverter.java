package org.poseibon.rac.application.converter;

import org.poseibon.rac.sdk.rdo.dimension.DimensionNodeComplexRdo;
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
public interface DimensionNodeEntity2ComplexRdoConverter extends
        Entity2RdoExtConverter<DimensionNodeEntity, DimensionNodeComplexRdo> {
    /**
     * 实例
     */
    DimensionNodeEntity2ComplexRdoConverter INSTANCE = Mappers.getMapper(DimensionNodeEntity2ComplexRdoConverter.class);
}
