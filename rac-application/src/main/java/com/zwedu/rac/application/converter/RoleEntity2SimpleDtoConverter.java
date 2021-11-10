package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rpo.role.RoleSimpleRpo;
import com.zwedu.rac.domain.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 角色entity-dto转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface RoleEntity2SimpleDtoConverter extends Entity2RdoConverter<RoleEntity, RoleSimpleRpo> {
    /**
     * 实例
     */
    RoleEntity2SimpleDtoConverter INSTANCE = Mappers.getMapper(RoleEntity2SimpleDtoConverter.class);
}
