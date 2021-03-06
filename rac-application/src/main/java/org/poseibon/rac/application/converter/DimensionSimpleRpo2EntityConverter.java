package org.poseibon.rac.application.converter;

import org.poseibon.rac.sdk.rpo.dimension.DimensionSimpleRpo;
import org.poseibon.rac.domain.entity.DimensionEntity;
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
public interface DimensionSimpleRpo2EntityConverter extends Rpo2EntityConverter<DimensionSimpleRpo, DimensionEntity> {
    /**
     * 实例
     */
    DimensionSimpleRpo2EntityConverter INSTANCE = Mappers.getMapper(DimensionSimpleRpo2EntityConverter.class);
}