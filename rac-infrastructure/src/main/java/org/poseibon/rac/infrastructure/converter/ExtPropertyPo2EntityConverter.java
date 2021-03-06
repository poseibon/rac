package org.poseibon.rac.infrastructure.converter;

import org.poseibon.rac.domain.entity.ExtPropertyEntity;
import org.poseibon.rac.infrastructure.po.ExtPropertyPo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Po2EntityConverter;

/**
 * 扩展属性po-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface ExtPropertyPo2EntityConverter extends Po2EntityConverter<ExtPropertyPo, ExtPropertyEntity> {
    /**
     * 实例
     */
    ExtPropertyPo2EntityConverter INSTANCE = Mappers.getMapper(ExtPropertyPo2EntityConverter.class);
}
