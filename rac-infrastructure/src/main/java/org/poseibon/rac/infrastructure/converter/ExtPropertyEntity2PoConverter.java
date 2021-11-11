package org.poseibon.rac.infrastructure.converter;

import org.poseibon.rac.domain.entity.ExtPropertyEntity;
import org.poseibon.rac.infrastructure.po.ExtPropertyPo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Entity2PoConverter;

/**
 * 扩展属性po-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface ExtPropertyEntity2PoConverter extends Entity2PoConverter<ExtPropertyEntity, ExtPropertyPo> {
    /**
     * 实例
     */
    ExtPropertyEntity2PoConverter INSTANCE = Mappers.getMapper(ExtPropertyEntity2PoConverter.class);
}
