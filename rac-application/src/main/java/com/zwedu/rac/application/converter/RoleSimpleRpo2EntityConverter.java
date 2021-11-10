package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rpo.role.RoleSimpleRpo;
import com.zwedu.rac.domain.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Rpo2EntityConverter;

/**
 * 角色dto-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface RoleSimpleRpo2EntityConverter extends Rpo2EntityConverter<RoleSimpleRpo, RoleEntity> {
    /**
     * 实例
     */
    RoleSimpleRpo2EntityConverter INSTANCE = Mappers.getMapper(RoleSimpleRpo2EntityConverter.class);
}