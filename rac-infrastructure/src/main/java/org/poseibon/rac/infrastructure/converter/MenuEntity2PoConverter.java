package org.poseibon.rac.infrastructure.converter;

import org.poseibon.rac.domain.entity.MenuEntity;
import org.poseibon.rac.infrastructure.po.MenuPo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Entity2PoConverter;

/**
 * 菜单po-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface MenuEntity2PoConverter extends Entity2PoConverter<MenuEntity, MenuPo> {
    /**
     * 实例
     */
    MenuEntity2PoConverter INSTANCE = Mappers.getMapper(MenuEntity2PoConverter.class);
}
