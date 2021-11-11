package org.poseibon.rac.application.converter;

import org.poseibon.rac.domain.entity.UserEntity;
import org.poseibon.rac.sdk.rdo.user.UserComplexRdo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用户entity-rdo转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface UserEntity2ComplexRdoConverter extends
        Entity2RdoExtConverter<UserEntity, UserComplexRdo> {
    /**
     * 实例
     */
    UserEntity2ComplexRdoConverter INSTANCE = Mappers.getMapper(UserEntity2ComplexRdoConverter.class);
}
