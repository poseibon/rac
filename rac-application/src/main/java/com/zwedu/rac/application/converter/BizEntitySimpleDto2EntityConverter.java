package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rpo.bizentity.BizEntitySimpleRpo;
import com.zwedu.rac.domain.entity.BizEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 业务实体dto-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface BizEntitySimpleDto2EntityConverter extends Dto2EntityConverter<BizEntitySimpleRpo, BizEntity> {
    /**
     * 实例
     */
    BizEntitySimpleDto2EntityConverter INSTANCE = Mappers.getMapper(BizEntitySimpleDto2EntityConverter.class);
}