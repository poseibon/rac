package com.zwedu.rac.application.converter;

import com.zwedu.rac.domain.entity.BizLineEntity;
import com.zwedu.rac.sdk.rpo.bizline.BizLineSimpleRpo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Rpo2EntityConverter;

/**
 * 业务线dto-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface BizLineSimpleRpo2EntityConverter extends Rpo2EntityConverter<BizLineSimpleRpo, BizLineEntity> {
    /**
     * 实例
     */
    BizLineSimpleRpo2EntityConverter INSTANCE = Mappers.getMapper(BizLineSimpleRpo2EntityConverter.class);
}