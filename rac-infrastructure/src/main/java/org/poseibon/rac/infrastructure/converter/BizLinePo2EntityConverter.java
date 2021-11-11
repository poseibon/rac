package org.poseibon.rac.infrastructure.converter;

import org.poseibon.rac.domain.entity.BizLineEntity;
import org.poseibon.rac.infrastructure.po.BizLinePo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Po2EntityConverter;

/**
 * 业务线po-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface BizLinePo2EntityConverter extends Po2EntityConverter<BizLinePo, BizLineEntity> {
    /**
     * 实例
     */
    BizLinePo2EntityConverter INSTANCE = Mappers.getMapper(BizLinePo2EntityConverter.class);
}
