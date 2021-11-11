package com.zwedu.rac.application.converter;

import com.zwedu.rac.domain.entity.DimensionNodeEntity;
import com.zwedu.rac.sdk.rdo.dimension.DimensionNodeRdo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 维度entity-rdo转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface DimensionNodeEntity2RdoConverter extends
        Entity2RdoExtConverter<DimensionNodeEntity, DimensionNodeRdo> {
    /**
     * 实例
     */
    DimensionNodeEntity2RdoConverter INSTANCE = Mappers.getMapper(DimensionNodeEntity2RdoConverter.class);
}
