package org.poseibon.rac.infrastructure.converter;

import org.poseibon.rac.domain.entity.UserEntity;
import org.poseibon.rac.infrastructure.po.UserPo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Po2EntityConverter;

/**
 * 用户po-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface UserPo2EntityConverter extends Po2EntityConverter<UserPo, UserEntity> {
    /**
     * 实例
     */
    UserPo2EntityConverter INSTANCE = Mappers.getMapper(UserPo2EntityConverter.class);
}
