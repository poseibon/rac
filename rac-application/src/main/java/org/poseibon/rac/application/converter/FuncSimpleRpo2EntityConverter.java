package org.poseibon.rac.application.converter;

import org.poseibon.rac.sdk.rpo.func.FuncSimpleRpo;
import org.poseibon.rac.domain.entity.FuncEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Rpo2EntityConverter;

/**
 * 功能rpo-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface FuncSimpleRpo2EntityConverter extends Rpo2EntityConverter<FuncSimpleRpo, FuncEntity> {
    /**
     * 实例
     */
    FuncSimpleRpo2EntityConverter INSTANCE = Mappers.getMapper(FuncSimpleRpo2EntityConverter.class);
}