package com.zwedu.rac.application.converter;

import com.zwedu.rac.domain.entity.UserEntity;
import com.zwedu.rac.sdk.rpo.user.UserSimpleRpo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用户dto-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface UserSimpleDto2EntityConverter extends Dto2EntityConverter<UserSimpleRpo, UserEntity> {
    /**
     * 实例
     */
    UserSimpleDto2EntityConverter INSTANCE = Mappers.getMapper(UserSimpleDto2EntityConverter.class);
}