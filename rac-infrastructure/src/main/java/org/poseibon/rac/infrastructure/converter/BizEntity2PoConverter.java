package org.poseibon.rac.infrastructure.converter;

import org.poseibon.rac.domain.entity.BizEntity;
import org.poseibon.rac.infrastructure.po.BizEntityPo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Entity2PoConverter;

/**
 * 业务实体po-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface BizEntity2PoConverter extends Entity2PoConverter<BizEntity, BizEntityPo> {
    /**
     * 实例
     */
    BizEntity2PoConverter INSTANCE = Mappers.getMapper(BizEntity2PoConverter.class);
}
