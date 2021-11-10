package com.zwedu.rac.application.converter;

import com.zwedu.rac.domain.entity.BizLineEntity;
import com.zwedu.rac.sdk.rpo.bizline.BizLineSimpleRpo;
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
public interface BizLineEntity2SimpleRdoConverter extends Entity2RdoExtConverter<BizLineEntity, BizLineSimpleRpo> {
    /**
     * 实例
     */
    BizLineEntity2SimpleRdoConverter INSTANCE = Mappers.getMapper(BizLineEntity2SimpleRdoConverter.class);
}
