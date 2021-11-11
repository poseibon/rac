package org.poseibon.rac.infrastructure.converter;

import org.poseibon.rac.domain.entity.ExtDataEntity;
import org.poseibon.rac.infrastructure.po.ExtDataPo;
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
public interface ExtDataEntity2PoConverter extends Entity2PoConverter<ExtDataEntity, ExtDataPo> {
    /**
     * 实例
     */
    ExtDataEntity2PoConverter INSTANCE = Mappers.getMapper(ExtDataEntity2PoConverter.class);
}
