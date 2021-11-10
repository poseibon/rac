package com.zwedu.rac.application.service;

import com.zwedu.rac.application.converter.BizEntity2ComplexDtoConverter;
import com.zwedu.rac.application.converter.BizEntity2SimpleDtoConverter;
import com.zwedu.rac.application.converter.BizEntitySimpleDto2EntityConverter;
import com.zwedu.rac.domain.entity.BizEntity;
import com.zwedu.rac.domain.service.BizEntityDomainService;
import com.zwedu.rac.rowauth.annotation.WriteAuth;
import com.zwedu.rac.sdk.rpo.base.ReqPaginationRpo;
import com.zwedu.rac.sdk.rpo.base.ResPaginationRpo;
import com.zwedu.rac.sdk.rpo.bizentity.BizEntityComplexDto;
import com.zwedu.rac.sdk.rpo.bizentity.BizEntitySimpleRpo;
import org.poseibon.common.page.Pagination;
import org.poseibon.common.validator.ParamAssert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 业务实体应用层服务
 *
 * @author qingchuan
 * @date 2020/12/10
 */
@Service
public class BizEntityAppService {
    @Resource
    private BizEntityDomainService bizEntityDomainService;

    /**
     * 查询业务实体列表数据
     *
     * @param record 分页查询参数
     * @return 业务实体列表数据
     */
    @WriteAuth
    public ResPaginationRpo<BizEntityComplexDto> listPage(ReqPaginationRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        // 查询对应的业务实体列表
        Pagination<BizEntity> pagination = bizEntityDomainService.listPage(record.getPageNo(),
                record.getPageSize(), record.getBizLineId(), record.getSearchVal());
        return BizEntity2ComplexDtoConverter.INSTANCE.toPaginationRdo(pagination);
    }

    /**
     * 查询授权的业务实体列表
     *
     * @return 业务实体列表数据
     */
    public List<BizEntitySimpleRpo> listByBizLineId(BizEntitySimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        return BizEntity2SimpleDtoConverter.INSTANCE.toRdoList(bizEntityDomainService
                .listByBizLineId(record.getBizLineId()));
    }

    /**
     * 创建业务实体
     *
     * @param currentUserId 登录用户ID
     * @param record 业务实体实体
     */
    @WriteAuth
    public void create(Long currentUserId, BizEntitySimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        bizEntityDomainService.create(currentUserId, BizEntitySimpleDto2EntityConverter.INSTANCE.toEntity(record));
    }

    /**
     * 更新业务实体
     *
     * @param currentUserId 登录用户ID
     * @param record 业务实体实体
     */
    @WriteAuth
    public void edit(Long currentUserId, BizEntitySimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        bizEntityDomainService.edit(currentUserId, BizEntitySimpleDto2EntityConverter.INSTANCE.toEntity(record));
    }


    /**
     * 删除业务实体
     *
     * @param currentUserId 登录用户ID
     * @param record 记录数据
     */
    @WriteAuth
    public void delete(Long currentUserId, BizEntitySimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        bizEntityDomainService.delete(currentUserId, record.getBizLineId(), record.getId());
    }


}
