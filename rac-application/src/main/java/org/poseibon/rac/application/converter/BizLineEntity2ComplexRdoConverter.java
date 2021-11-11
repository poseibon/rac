package org.poseibon.rac.application.converter;

import org.poseibon.rac.sdk.rpo.bizline.BizLineComplexRdo;
import org.poseibon.rac.domain.entity.BizLineEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 业务线entity-rdo转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface BizLineEntity2ComplexRdoConverter extends
        Entity2RdoExtConverter<BizLineEntity, BizLineComplexRdo> {
    /**
     * 实例
     */
    BizLineEntity2ComplexRdoConverter INSTANCE = Mappers.getMapper(BizLineEntity2ComplexRdoConverter.class);
}
