package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rpo.func.FuncSimpleRpo;
import com.zwedu.rac.domain.entity.FuncEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Entity2RdoConverter;

/**
 * 功能entity-dto转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface FuncEntity2SimpleRdoConverter extends Entity2RdoExtConverter<FuncEntity, FuncSimpleRpo> {
    /**
     * 实例
     */
    FuncEntity2SimpleRdoConverter INSTANCE = Mappers.getMapper(FuncEntity2SimpleRdoConverter.class);
}
