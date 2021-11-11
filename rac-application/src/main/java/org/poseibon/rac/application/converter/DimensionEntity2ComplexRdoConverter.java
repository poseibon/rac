package org.poseibon.rac.application.converter;

import org.poseibon.rac.sdk.rdo.dimension.DimensionComplexRdo;
import org.poseibon.rac.domain.entity.DimensionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 维度entity-rdo转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface DimensionEntity2ComplexRdoConverter extends
        Entity2RdoExtConverter<DimensionEntity, DimensionComplexRdo> {
    /**
     * 实例
     */
    DimensionEntity2ComplexRdoConverter INSTANCE = Mappers.getMapper(DimensionEntity2ComplexRdoConverter.class);
}
