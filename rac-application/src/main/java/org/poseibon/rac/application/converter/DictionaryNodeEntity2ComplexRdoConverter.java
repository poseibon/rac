package org.poseibon.rac.application.converter;

import org.poseibon.rac.sdk.rdo.dictionary.DictionaryNodeComplexRdo;
import org.poseibon.rac.domain.entity.DictionaryNodeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 字典entity-rdo转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface DictionaryNodeEntity2ComplexRdoConverter extends
        Entity2RdoExtConverter<DictionaryNodeEntity, DictionaryNodeComplexRdo> {
    /**
     * 实例
     */
    DictionaryNodeEntity2ComplexRdoConverter INSTANCE = Mappers.getMapper(DictionaryNodeEntity2ComplexRdoConverter.class);
}
