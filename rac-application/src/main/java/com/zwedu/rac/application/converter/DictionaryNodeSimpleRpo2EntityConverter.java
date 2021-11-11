package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rpo.dictionary.DictionaryNodeSimpleRpo;
import com.zwedu.rac.domain.entity.DictionaryNodeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Rpo2EntityConverter;

/**
 * 字典rpo-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface DictionaryNodeSimpleRpo2EntityConverter extends Rpo2EntityConverter
        <DictionaryNodeSimpleRpo, DictionaryNodeEntity> {
    /**
     * 实例
     */
    DictionaryNodeSimpleRpo2EntityConverter INSTANCE = Mappers.getMapper(DictionaryNodeSimpleRpo2EntityConverter.class);
}