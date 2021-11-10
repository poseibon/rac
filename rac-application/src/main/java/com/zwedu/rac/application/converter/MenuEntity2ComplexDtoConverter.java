package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rpo.menu.MenuComplexDto;
import com.zwedu.rac.domain.entity.MenuEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 菜单entity-dto转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface MenuEntity2ComplexDtoConverter extends
        Entity2RdoConverter<MenuEntity, MenuComplexDto> {
    /**
     * 实例
     */
    MenuEntity2ComplexDtoConverter INSTANCE = Mappers.getMapper(MenuEntity2ComplexDtoConverter.class);
}
