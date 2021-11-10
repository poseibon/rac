package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rpo.dimension.DimensionComplexDto;
import com.zwedu.rac.domain.entity.DimensionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Entity2RdoConverter;

/**
 * 维度entity-dto转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface DimensionEntity2ComplexRdoConverter extends
        Entity2RdoExtConverter<DimensionEntity, DimensionComplexDto> {
    /**
     * 实例
     */
    DimensionEntity2ComplexRdoConverter INSTANCE = Mappers.getMapper(DimensionEntity2ComplexRdoConverter.class);
}
