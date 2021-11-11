package org.poseibon.rac.infrastructure.converter;

import org.poseibon.rac.domain.entity.ExtDataEntity;
import org.poseibon.rac.infrastructure.po.ExtDataPo;
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
public interface ExtDataPo2EntityConverter extends Po2EntityConverter<ExtDataPo, ExtDataEntity> {
    /**
     * 实例
     */
    ExtDataPo2EntityConverter INSTANCE = Mappers.getMapper(ExtDataPo2EntityConverter.class);
}
