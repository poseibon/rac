package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rdo.dictionary.DictionaryComplexRdo;
import com.zwedu.rac.domain.entity.DictionaryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 字典entity-rdo转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface DictionaryEntity2ComplexRdoConverter extends
        Entity2RdoExtConverter<DictionaryEntity, DictionaryComplexRdo> {
    /**
     * 实例
     */
    DictionaryEntity2ComplexRdoConverter INSTANCE = Mappers.getMapper(DictionaryEntity2ComplexRdoConverter.class);
}
