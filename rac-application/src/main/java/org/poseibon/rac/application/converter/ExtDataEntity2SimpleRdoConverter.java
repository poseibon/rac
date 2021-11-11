package org.poseibon.rac.application.converter;

import org.poseibon.rac.sdk.rpo.ext.ExtDataSimpleRpo;
import org.poseibon.rac.domain.entity.ExtDataEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 扩展属性entity-rdo转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface ExtDataEntity2SimpleRdoConverter extends
        Entity2RdoExtConverter<ExtDataEntity, ExtDataSimpleRpo> {
    /**
     * 实例
     */
    ExtDataEntity2SimpleRdoConverter INSTANCE = Mappers.getMapper(ExtDataEntity2SimpleRdoConverter.class);
}
