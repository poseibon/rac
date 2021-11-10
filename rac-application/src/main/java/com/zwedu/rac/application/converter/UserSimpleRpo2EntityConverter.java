package com.zwedu.rac.application.converter;

import com.zwedu.rac.domain.entity.UserEntity;
import com.zwedu.rac.sdk.rdo.user.UserSimpleRdo;
import com.zwedu.rac.sdk.rpo.user.UserSimpleRpo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Rpo2EntityConverter;

/**
 * 用户dto-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface UserSimpleRpo2EntityConverter extends Rpo2EntityConverter<UserSimpleRpo, UserEntity> {
    /**
     * 实例
     */
    UserSimpleRpo2EntityConverter INSTANCE = Mappers.getMapper(UserSimpleRpo2EntityConverter.class);
}