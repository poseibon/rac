package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rpo.dimension.DimensionSimpleRpo;
import com.zwedu.rac.domain.entity.DimensionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 维度entity-dto转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface DimensionEntity2SimpleDtoConverter extends Entity2RdoConverter
        <DimensionEntity, DimensionSimpleRpo> {
    /**
     * 实例
     */
    DimensionEntity2SimpleDtoConverter INSTANCE = Mappers.getMapper(DimensionEntity2SimpleDtoConverter.class);
}
