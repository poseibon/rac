package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rpo.dimension.DimensionNodeSimpleRpo;
import com.zwedu.rac.domain.entity.DimensionNodeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 维度entity-dto转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface DimensionNodeEntity2SimpleDtoConverter extends Entity2RdoConverter<DimensionNodeEntity, DimensionNodeSimpleRpo> {
    /**
     * 实例
     */
    DimensionNodeEntity2SimpleDtoConverter INSTANCE = Mappers.getMapper(DimensionNodeEntity2SimpleDtoConverter.class);
}
