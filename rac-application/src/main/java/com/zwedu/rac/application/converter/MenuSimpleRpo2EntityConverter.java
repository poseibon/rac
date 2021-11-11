package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rpo.menu.MenuSimpleRpo;
import com.zwedu.rac.domain.entity.MenuEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Rpo2EntityConverter;

/**
 * 菜单rpo-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface MenuSimpleRpo2EntityConverter extends Rpo2EntityConverter<MenuSimpleRpo, MenuEntity> {
    /**
     * 实例
     */
    MenuSimpleRpo2EntityConverter INSTANCE = Mappers.getMapper(MenuSimpleRpo2EntityConverter.class);
}