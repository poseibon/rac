package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rpo.func.FuncSimpleRpo;
import com.zwedu.rac.domain.entity.FuncEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 功能entity-dto转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface FuncEntity2SimpleDtoConverter extends Entity2RdoConverter<FuncEntity, FuncSimpleRpo> {
    /**
     * 实例
     */
    FuncEntity2SimpleDtoConverter INSTANCE = Mappers.getMapper(FuncEntity2SimpleDtoConverter.class);
}
