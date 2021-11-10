package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rpo.dimension.DimensionNodeSimpleRdo;
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
public interface DimensionNodeEntity2SimpleRdoConverter extends Entity2RdoExtConverter<DimensionNodeEntity, DimensionNodeSimpleRdo> {
    /**
     * 实例
     */
    DimensionNodeEntity2SimpleRdoConverter INSTANCE = Mappers.getMapper(DimensionNodeEntity2SimpleRdoConverter.class);
}
