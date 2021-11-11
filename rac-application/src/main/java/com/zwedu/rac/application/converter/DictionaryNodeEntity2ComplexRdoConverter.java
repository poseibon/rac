package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rdo.dictionary.DictionaryNodeComplexRdo;
import com.zwedu.rac.domain.entity.DictionaryNodeEntity;
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
