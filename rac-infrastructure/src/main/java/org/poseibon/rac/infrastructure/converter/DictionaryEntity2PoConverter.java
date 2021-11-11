package org.poseibon.rac.infrastructure.converter;

import org.poseibon.rac.domain.entity.DictionaryEntity;
import org.poseibon.rac.infrastructure.po.DictionaryPo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Entity2PoConverter;

/**
 * 字典po-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface DictionaryEntity2PoConverter extends Entity2PoConverter<DictionaryEntity, DictionaryPo> {
    /**
     * 实例
     */
    DictionaryEntity2PoConverter INSTANCE = Mappers.getMapper(DictionaryEntity2PoConverter.class);
}
