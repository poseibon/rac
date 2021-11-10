package com.zwedu.rac.application.converter;

import com.zwedu.rac.sdk.rpo.bizentity.BizEntityComplexRdo;
import com.zwedu.rac.domain.entity.BizEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Entity2RdoConverter;

/**
 * 业务实体entity-dto转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface BizEntity2ComplexRdoConverter extends
        Entity2RdoExtConverter<BizEntity, BizEntityComplexRdo> {
    /**
     * 实例
     */
    BizEntity2ComplexRdoConverter INSTANCE = Mappers.getMapper(BizEntity2ComplexRdoConverter.class);
}
