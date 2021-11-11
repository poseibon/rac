package org.poseibon.rac.application.converter;

import org.poseibon.rac.sdk.rpo.ext.ExtPropertySimpleRpo;
import org.poseibon.rac.domain.entity.ExtPropertyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Rpo2EntityConverter;

/**
 * 扩展属性rpo-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface ExtPropertySimpleRpo2EntityConverter extends Rpo2EntityConverter<ExtPropertySimpleRpo, ExtPropertyEntity> {
    /**
     * 实例
     */
    ExtPropertySimpleRpo2EntityConverter INSTANCE = Mappers.getMapper(ExtPropertySimpleRpo2EntityConverter.class);
}