package com.zwedu.rac.application.converter;

import com.zwedu.rac.domain.entity.RoleEntity;
import com.zwedu.rac.sdk.rdo.role.RoleSimpleRdo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 角色entity-rdo转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface RoleEntity2SimpleRdoConverter extends Entity2RdoExtConverter<RoleEntity, RoleSimpleRdo> {
    /**
     * 实例
     */
    RoleEntity2SimpleRdoConverter INSTANCE = Mappers.getMapper(RoleEntity2SimpleRdoConverter.class);
}
