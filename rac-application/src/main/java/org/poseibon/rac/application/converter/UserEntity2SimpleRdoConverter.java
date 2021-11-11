package org.poseibon.rac.application.converter;

import org.poseibon.rac.domain.entity.UserEntity;
import org.poseibon.rac.sdk.rdo.user.UserSimpleRdo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用户entity-rdo转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface UserEntity2SimpleRdoConverter extends Entity2RdoExtConverter<UserEntity, UserSimpleRdo> {
    /**
     * 实例
     */
    UserEntity2SimpleRdoConverter INSTANCE = Mappers.getMapper(UserEntity2SimpleRdoConverter.class);
}
