package org.poseibon.rac.infrastructure.converter;

import org.poseibon.rac.domain.entity.DictionaryNodeEntity;
import org.poseibon.rac.infrastructure.po.DictionaryNodePo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Entity2PoConverter;

/**
 * 字典节点po-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface DictionaryNodeEntity2PoConverter extends Entity2PoConverter<DictionaryNodeEntity, DictionaryNodePo> {
    /**
     * 实例
     */
    DictionaryNodeEntity2PoConverter INSTANCE = Mappers.getMapper(DictionaryNodeEntity2PoConverter.class);
}
