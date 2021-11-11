package org.poseibon.rac.infrastructure.converter;

import org.poseibon.rac.domain.entity.UserEntity;
import org.poseibon.rac.infrastructure.po.UserPo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Entity2PoConverter;

/**
 * 用户po-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface UserEntity2PoConverter extends Entity2PoConverter<UserEntity, UserPo> {
    /**
     * 实例
     */
    UserEntity2PoConverter INSTANCE = Mappers.getMapper(UserEntity2PoConverter.class);
}
