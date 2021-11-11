package org.poseibon.rac.infrastructure.converter;

import org.poseibon.rac.domain.entity.RoleEntity;
import org.poseibon.rac.infrastructure.po.RolePo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Entity2PoConverter;

/**
 * 角色po-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface RoleEntity2PoConverter extends Entity2PoConverter<RoleEntity, RolePo> {
    /**
     * 实例
     */
    RoleEntity2PoConverter INSTANCE = Mappers.getMapper(RoleEntity2PoConverter.class);
}
