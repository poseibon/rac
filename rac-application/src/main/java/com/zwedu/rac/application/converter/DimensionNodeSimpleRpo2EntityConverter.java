package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rpo.dimension.DimensionNodeSimpleRdo;
import com.zwedu.rac.domain.entity.DimensionNodeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Rpo2EntityConverter;

/**
 * 维度dto-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface DimensionNodeSimpleRpo2EntityConverter extends Rpo2EntityConverter<DimensionNodeSimpleRdo, DimensionNodeEntity> {
    /**
     * 实例
     */
    DimensionNodeSimpleRpo2EntityConverter INSTANCE = Mappers.getMapper(DimensionNodeSimpleRpo2EntityConverter.class);
}