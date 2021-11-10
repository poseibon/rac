package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rpo.role.RoleComplexRpo;
import com.zwedu.rac.domain.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Entity2RdoConverter;

/**
 * 角色entity-dto转换器
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
