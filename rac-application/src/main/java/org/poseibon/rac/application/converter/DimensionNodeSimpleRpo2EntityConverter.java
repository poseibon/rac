package org.poseibon.rac.application.converter;

import org.poseibon.rac.domain.entity.DimensionNodeEntity;
import org.poseibon.rac.sdk.rpo.dimension.DimensionNodeSimpleRpo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Rpo2EntityConverter;

/**
 * 维度rpo-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface DimensionNodeSimpleRpo2EntityConverter extends Rpo2EntityConverter<DimensionNodeSimpleRpo, DimensionNodeEntity> {
    /**
     * 实例
     */
    DimensionNodeSimpleRpo2EntityConverter INSTANCE = Mappers.getMapper(DimensionNodeSimpleRpo2EntityConverter.class);
}