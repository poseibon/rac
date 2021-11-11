package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rdo.menu.MenuComplexRdo;
import com.zwedu.rac.domain.entity.MenuEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 菜单entity-rdo转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface MenuEntity2ComplexRdoConverter extends
        Entity2RdoExtConverter<MenuEntity, MenuComplexRdo> {
    /**
     * 实例
     */
    MenuEntity2ComplexRdoConverter INSTANCE = Mappers.getMapper(MenuEntity2ComplexRdoConverter.class);
}
