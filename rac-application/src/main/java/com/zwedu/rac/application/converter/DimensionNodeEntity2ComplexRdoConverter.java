package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rpo.dimension.DimensionNodeComplexDto;
import com.zwedu.rac.domain.entity.DimensionNodeEntity;
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
public interface DimensionNodeEntity2ComplexRdoConverter extends
        Entity2RdoExtConverter<DimensionNodeEntity, DimensionNodeComplexDto> {
    /**
     * 实例
     */
    DimensionNodeEntity2ComplexRdoConverter INSTANCE = Mappers.getMapper(DimensionNodeEntity2ComplexRdoConverter.class);
}
