package com.zwedu.rac.application.converter;

import com.zwedu.rac.domain.entity.UserEntity;
import com.zwedu.rac.sdk.rpo.user.UserSimpleRpo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用户entity-dto转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface UserEntity2SimpleDtoConverter extends Entity2DtoConverter<UserEntity, UserSimpleRpo> {
    /**
     * 实例
     */
    UserEntity2SimpleDtoConverter INSTANCE = Mappers.getMapper(UserEntity2SimpleDtoConverter.class);
}
