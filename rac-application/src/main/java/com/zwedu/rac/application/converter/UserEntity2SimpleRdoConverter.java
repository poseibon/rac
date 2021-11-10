package com.zwedu.rac.application.converter;

import com.zwedu.rac.domain.entity.UserEntity;
import com.zwedu.rac.sdk.rdo.user.UserSimpleRdo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用户entity-dto转换器
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
