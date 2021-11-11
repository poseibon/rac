package org.poseibon.rac.infrastructure.converter;

import org.poseibon.rac.domain.entity.BizLineEntity;
import org.poseibon.rac.infrastructure.po.BizLinePo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Entity2PoConverter;

/**
 * 业务线po-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface BizLineEntity2PoConverter extends Entity2PoConverter<BizLineEntity, BizLinePo> {
    /**
     * 实例
     */
    BizLineEntity2PoConverter INSTANCE = Mappers.getMapper(BizLineEntity2PoConverter.class);
}
