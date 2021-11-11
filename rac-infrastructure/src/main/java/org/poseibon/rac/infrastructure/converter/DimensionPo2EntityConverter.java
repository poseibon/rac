package org.poseibon.rac.infrastructure.converter;

import org.poseibon.rac.domain.entity.DimensionEntity;
import org.poseibon.rac.infrastructure.po.DimensionPo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Po2EntityConverter;

/**
 * 维度po-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface DimensionPo2EntityConverter extends Po2EntityConverter<DimensionPo, DimensionEntity> {
    /**
     * 实例
     */
    DimensionPo2EntityConverter INSTANCE = Mappers.getMapper(DimensionPo2EntityConverter.class);
}
