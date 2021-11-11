package com.zwedu.rac.application.converter;

import com.zwedu.rac.domain.entity.ExtDataEntity;
import com.zwedu.rac.sdk.rdo.ext.ExtPropertyRdo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 扩展属性entity-rdo转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface ExtDataEntity2RdoConverter extends
        Entity2RdoExtConverter<ExtDataEntity, ExtPropertyRdo> {
    /**
     * 实例
     */
    ExtDataEntity2RdoConverter INSTANCE = Mappers.getMapper(ExtDataEntity2RdoConverter.class);
}
