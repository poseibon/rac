package org.poseibon.rac.infrastructure.converter;

import org.poseibon.rac.domain.entity.DimensionNodeEntity;
import org.poseibon.rac.infrastructure.po.DimensionNodePo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Entity2PoConverter;

/**
 * 维度节点po-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface DimensionNodeEntity2PoConverter extends Entity2PoConverter<DimensionNodeEntity, DimensionNodePo> {
    /**
     * 实例
     */
    DimensionNodeEntity2PoConverter INSTANCE = Mappers.getMapper(DimensionNodeEntity2PoConverter.class);
}
