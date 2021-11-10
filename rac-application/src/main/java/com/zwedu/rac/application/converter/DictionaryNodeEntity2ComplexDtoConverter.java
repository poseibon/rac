package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rpo.dictionary.DictionaryNodeComplexDto;
import com.zwedu.rac.domain.entity.DictionaryNodeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 字典entity-dto转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface DictionaryNodeEntity2ComplexDtoConverter extends
        Entity2RdoConverter<DictionaryNodeEntity, DictionaryNodeComplexDto> {
    /**
     * 实例
     */
    DictionaryNodeEntity2ComplexDtoConverter INSTANCE = Mappers.getMapper(DictionaryNodeEntity2ComplexDtoConverter.class);
}
