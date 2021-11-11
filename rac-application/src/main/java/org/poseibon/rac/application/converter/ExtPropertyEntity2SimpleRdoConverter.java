package org.poseibon.rac.application.converter;

import org.poseibon.rac.domain.entity.ExtPropertyEntity;
import org.poseibon.rac.sdk.rdo.ext.ExtPropertySimpleRdo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 扩展属性entity-rdo转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface ExtPropertyEntity2SimpleRdoConverter extends
        Entity2RdoExtConverter<ExtPropertyEntity, ExtPropertySimpleRdo> {
    /**
     * 实例
     */
    ExtPropertyEntity2SimpleRdoConverter INSTANCE = Mappers.getMapper(ExtPropertyEntity2SimpleRdoConverter.class);
}
