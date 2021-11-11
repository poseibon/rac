package com.zwedu.rac.application.converter;

import com.zwedu.rac.domain.entity.ExtPropertyEntity;
import com.zwedu.rac.sdk.rdo.ext.ExtPropertySimpleRdo;
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
