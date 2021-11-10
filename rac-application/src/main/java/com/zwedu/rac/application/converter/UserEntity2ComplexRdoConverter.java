package com.zwedu.rac.application.converter;

import com.zwedu.rac.domain.entity.UserEntity;
import com.zwedu.rac.sdk.rpo.user.UserComplexRdo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用户entity-dto转换器
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
