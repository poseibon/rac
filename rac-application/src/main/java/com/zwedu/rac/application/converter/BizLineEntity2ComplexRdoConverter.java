package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rpo.bizline.BizLineComplexDto;
import com.zwedu.rac.domain.entity.BizLineEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Entity2RdoConverter;

/**
 * 业务线entity-dto转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface BizLineEntity2ComplexRdoConverter extends
        Entity2RdoExtConverter<BizLineEntity, BizLineComplexDto> {
    /**
     * 实例
     */
    BizLineEntity2ComplexRdoConverter INSTANCE = Mappers.getMapper(BizLineEntity2ComplexRdoConverter.class);
}
