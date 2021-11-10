package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rpo.bizentity.BizEntityComplexDto;
import com.zwedu.rac.domain.entity.BizEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 业务实体entity-dto转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface BizEntity2ComplexDtoConverter extends
        Entity2RdoConverter<BizEntity, BizEntityComplexDto> {
    /**
     * 实例
     */
    BizEntity2ComplexDtoConverter INSTANCE = Mappers.getMapper(BizEntity2ComplexDtoConverter.class);
}
