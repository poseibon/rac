package org.poseibon.rac.infrastructure.converter;

import org.poseibon.rac.domain.entity.BizEntity;
import org.poseibon.rac.infrastructure.po.BizEntityPo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.poseibon.common.converter.Po2EntityConverter;

/**
 * 业务实体po-entity转换器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Mapper
public interface BizEntityPo2EntityConverter extends Po2EntityConverter<BizEntityPo, BizEntity> {
    /**
     * 实例
     */
    BizEntityPo2EntityConverter INSTANCE = Mappers.getMapper(BizEntityPo2EntityConverter.class);
}
