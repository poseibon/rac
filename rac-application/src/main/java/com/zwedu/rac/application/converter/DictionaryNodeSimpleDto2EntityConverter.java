package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rpo.dictionary.DictionaryNodeSimpleRpo;
import com.zwedu.rac.domain.entity.DictionaryNodeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 字典dto-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface DictionaryNodeSimpleDto2EntityConverter extends Rpo2EntityConverter
        <DictionaryNodeSimpleRpo, DictionaryNodeEntity> {
    /**
     * 实例
     */
    DictionaryNodeSimpleDto2EntityConverter INSTANCE = Mappers.getMapper(DictionaryNodeSimpleDto2EntityConverter.class);
}