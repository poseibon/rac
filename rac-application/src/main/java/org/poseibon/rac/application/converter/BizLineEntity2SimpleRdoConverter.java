package org.poseibon.rac.application.converter;

import org.poseibon.rac.domain.entity.BizLineEntity;
import org.poseibon.rac.sdk.rdo.bizline.BizLineSimpleRdo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 业务线entity-rdo转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface BizLineEntity2SimpleRdoConverter extends Entity2RdoExtConverter<BizLineEntity, BizLineSimpleRdo> {
    /**
     * 实例
     */
    BizLineEntity2SimpleRdoConverter INSTANCE = Mappers.getMapper(BizLineEntity2SimpleRdoConverter.class);
}
