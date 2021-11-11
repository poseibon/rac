package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rdo.ext.ExtPropertyComplexRdo;
import com.zwedu.rac.domain.entity.ExtPropertyEntity;
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
