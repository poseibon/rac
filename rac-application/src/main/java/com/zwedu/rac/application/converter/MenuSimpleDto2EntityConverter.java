package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rpo.menu.MenuSimpleRpo;
import com.zwedu.rac.domain.entity.MenuEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 菜单dto-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface MenuSimpleDto2EntityConverter extends Dto2EntityConverter<MenuSimpleRpo, MenuEntity> {
    /**
     * 实例
     */
    MenuSimpleDto2EntityConverter INSTANCE = Mappers.getMapper(MenuSimpleDto2EntityConverter.class);
}