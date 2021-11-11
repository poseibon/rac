package org.poseibon.rac.application.converter;

import org.poseibon.rac.domain.entity.DictionaryEntity;
import org.poseibon.rac.sdk.rdo.dictionary.DictionarySimpleRdo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 字典entity-rdo转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface DictionaryEntity2SimpleRdoConverter extends Entity2RdoExtConverter
        <DictionaryEntity, DictionarySimpleRdo> {
    /**
     * 实例
     */
    DictionaryEntity2SimpleRdoConverter INSTANCE = Mappers.getMapper(DictionaryEntity2SimpleRdoConverter.class);
}
