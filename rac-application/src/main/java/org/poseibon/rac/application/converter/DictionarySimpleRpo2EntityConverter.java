package org.poseibon.rac.application.converter;

import org.poseibon.rac.sdk.rpo.dictionary.DictionarySimpleRpo;
import org.poseibon.rac.domain.entity.DictionaryEntity;
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
public interface DictionarySimpleRpo2EntityConverter extends Rpo2EntityConverter<DictionarySimpleRpo, DictionaryEntity> {
    /**
     * 实例
     */
    DictionarySimpleRpo2EntityConverter INSTANCE = Mappers.getMapper(DictionarySimpleRpo2EntityConverter.class);
}