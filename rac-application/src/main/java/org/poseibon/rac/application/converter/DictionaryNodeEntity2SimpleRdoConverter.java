package org.poseibon.rac.application.converter;

import org.poseibon.rac.domain.entity.DictionaryNodeEntity;
import org.poseibon.rac.sdk.rpo.dictionary.DictionaryNodeSimpleRpo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 字典entity-rdo转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface DictionaryNodeEntity2SimpleRdoConverter extends Entity2RdoExtConverter
        <DictionaryNodeEntity, DictionaryNodeSimpleRpo> {
    /**
     * 实例
     */
    DictionaryNodeEntity2SimpleRdoConverter INSTANCE = Mappers.getMapper(DictionaryNodeEntity2SimpleRdoConverter.class);
}
