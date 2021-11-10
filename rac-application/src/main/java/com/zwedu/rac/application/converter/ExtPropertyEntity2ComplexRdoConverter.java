package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rpo.ext.ExtPropertyComplexDto;
import com.zwedu.rac.domain.entity.ExtPropertyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Entity2RdoConverter;

/**
 * 扩展属性entity-dto转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface ExtPropertyEntity2ComplexRdoConverter extends
        Entity2RdoExtConverter<ExtPropertyEntity, ExtPropertyComplexDto> {
    /**
     * 实例
     */
    ExtPropertyEntity2ComplexRdoConverter INSTANCE = Mappers.getMapper(ExtPropertyEntity2ComplexRdoConverter.class);
}
