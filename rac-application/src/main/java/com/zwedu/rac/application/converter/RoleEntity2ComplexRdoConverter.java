package com.zwedu.rac.application.converter;

import com.zwedu.rac.domain.entity.RoleEntity;
import com.zwedu.rac.sdk.rpo.role.RoleComplexRpo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 角色entity-rdo转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface RoleEntity2ComplexRdoConverter extends
        Entity2RdoExtConverter<RoleEntity, RoleComplexRpo> {
    /**
     * 实例
     */
    RoleEntity2ComplexRdoConverter INSTANCE = Mappers.getMapper(RoleEntity2ComplexRdoConverter.class);
}
