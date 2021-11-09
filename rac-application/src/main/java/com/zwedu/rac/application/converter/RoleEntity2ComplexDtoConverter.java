package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rpo.role.RoleComplexRpo;
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
public interface RoleEntity2ComplexDtoConverter extends
        Entity2DtoConverter<RoleEntity, RoleComplexRpo> {
    /**
     * 实例
     */
    RoleEntity2ComplexDtoConverter INSTANCE = Mappers.getMapper(RoleEntity2ComplexDtoConverter.class);
}
