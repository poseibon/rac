package org.poseibon.rac.application.converter;

import org.poseibon.rac.sdk.rdo.ext.ExtPropertyComplexRdo;
import org.poseibon.rac.domain.entity.ExtPropertyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 扩展属性entity-rdo转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface ExtPropertyEntity2ComplexRdoConverter extends
        Entity2RdoExtConverter<ExtPropertyEntity, ExtPropertyComplexRdo> {
    /**
     * 实例
     */
    ExtPropertyEntity2ComplexRdoConverter INSTANCE = Mappers.getMapper(ExtPropertyEntity2ComplexRdoConverter.class);
}
