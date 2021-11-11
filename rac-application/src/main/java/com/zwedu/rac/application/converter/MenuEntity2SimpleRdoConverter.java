package com.zwedu.rac.application.converter;

import com.zwedu.rac.domain.entity.MenuEntity;
import com.zwedu.rac.sdk.rpo.menu.MenuSimpleRpo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 菜单entity-rdo转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface MenuEntity2SimpleRdoConverter extends Entity2RdoExtConverter
        <MenuEntity, MenuSimpleRpo> {
    /**
     * 实例
     */
    MenuEntity2SimpleRdoConverter INSTANCE = Mappers.getMapper(MenuEntity2SimpleRdoConverter.class);
}
