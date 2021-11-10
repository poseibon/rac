package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rpo.dictionary.DictionarySimpleRpo;
import com.zwedu.rac.domain.entity.DictionaryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Entity2RdoConverter;

/**
 * 字典entity-dto转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface DictionaryEntity2SimpleRdoConverter extends Entity2RdoExtConverter
        <DictionaryEntity, DictionarySimpleRpo> {
    /**
     * 实例
     */
    DictionaryEntity2SimpleRdoConverter INSTANCE = Mappers.getMapper(DictionaryEntity2SimpleRdoConverter.class);
}