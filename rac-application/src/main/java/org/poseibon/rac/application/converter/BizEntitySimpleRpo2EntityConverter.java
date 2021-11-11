package org.poseibon.rac.application.converter;

import org.poseibon.rac.sdk.rpo.bizentity.BizEntitySimpleRpo;
import org.poseibon.rac.domain.entity.BizEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Rpo2EntityConverter;

/**
 * 业务实体rpo-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface BizEntitySimpleRpo2EntityConverter extends Rpo2EntityConverter<BizEntitySimpleRpo, BizEntity> {
    /**
     * 实例
     */
    BizEntitySimpleRpo2EntityConverter INSTANCE = Mappers.getMapper(BizEntitySimpleRpo2EntityConverter.class);
}