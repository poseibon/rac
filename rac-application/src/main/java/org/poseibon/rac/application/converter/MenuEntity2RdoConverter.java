package org.poseibon.rac.application.converter;

import org.poseibon.rac.domain.entity.MenuEntity;
import org.poseibon.rac.sdk.rdo.menu.MenuRdo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 菜单entity-rdo转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface MenuEntity2RdoConverter extends
        Entity2RdoExtConverter<MenuEntity, MenuRdo> {
    /**
     * 实例
     */
    MenuEntity2RdoConverter INSTANCE = Mappers.getMapper(MenuEntity2RdoConverter.class);
}
