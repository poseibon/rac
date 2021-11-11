package org.poseibon.rac.application.converter;

import org.poseibon.rac.domain.entity.RoleEntity;
import org.poseibon.rac.sdk.rdo.role.RoleSimpleRdo;
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
