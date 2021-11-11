package org.poseibon.rac.application.converter;

import org.poseibon.rac.sdk.rpo.ext.ExtDataSimpleRpo;
import org.poseibon.rac.domain.entity.ExtDataEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Rpo2EntityConverter;

/**
 * 扩展属性值rpo-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface ExtDataSimpleRpo2EntityConverter extends Rpo2EntityConverter
        <ExtDataSimpleRpo, ExtDataEntity> {
    /**
     * 实例
     */
    ExtDataSimpleRpo2EntityConverter INSTANCE = Mappers.getMapper(ExtDataSimpleRpo2EntityConverter.class);
}