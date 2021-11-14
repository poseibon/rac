package org.poseibon.rac.application.service;

import org.poseibon.rac.application.converter.ExtPropertyEntity2ComplexRdoConverter;
import org.poseibon.rac.application.converter.ExtPropertyEntity2SimpleRdoConverter;
import org.poseibon.rac.application.converter.ExtPropertySimpleRpo2EntityConverter;
import org.poseibon.rac.rowauth.annotation.ReadAuth;
import org.poseibon.rac.sdk.rdo.base.PaginationRdo;
import org.poseibon.rac.sdk.rdo.base.PaginationRpo;
import org.poseibon.rac.sdk.rdo.ext.ExtPropertyComplexRdo;
import org.poseibon.rac.sdk.rdo.ext.ExtPropertySimpleRdo;
import org.poseibon.rac.sdk.rpo.ext.ExtPropertySimpleRpo;
import org.poseibon.rac.rowauth.annotation.WriteAuth;
import org.poseibon.common.page.Pagination;
import org.poseibon.common.validator.ParamAssert;
import org.poseibon.rac.domain.entity.ExtPropertyEntity;
import org.poseibon.rac.domain.service.ExtPropertyDomainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 扩展属性应用层服务
 *
 * @author qingchuan
 * @date 2020/12/10
 */
@Service
public class ExtPropertyAppService {
    @Resource
    private ExtPropertyDomainService extPropertyDomainService;

    /**
     * 查询扩展属性列表数据
     *
     * @param record 分页查询参数
     * @return 扩展属性列表数据
     */
    @ReadAuth
    public PaginationRdo<ExtPropertyComplexRdo> listPage(PaginationRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        // 查询对应的扩展属性列表
        Pagination<ExtPropertyEntity> pagination = extPropertyDomainService.listPage(record.getPageNo(),
                record.getPageSize(), record.getBizLineId(), record.getSearchVal());
        return ExtPropertyEntity2ComplexRdoConverter.INSTANCE.toPaginationRdo(pagination);
    }

    /**
     * 查询授权的扩展属性列表
     *
     * @return 扩展属性列表数据
     */
    @ReadAuth
    public List<ExtPropertySimpleRdo> listByBizLineId(ExtPropertySimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        return ExtPropertyEntity2SimpleRdoConverter.INSTANCE.toRdoList(extPropertyDomainService
                .listByBizLineId(record.getBizLineId()));
    }


    /**
     * 查询授权的扩展属性列表
     *
     * @return 扩展属性列表数据
     */
    @ReadAuth
    public List<ExtPropertySimpleRdo> listByBizEntityEnName(ExtPropertySimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        return ExtPropertyEntity2SimpleRdoConverter.INSTANCE.toRdoList(extPropertyDomainService
                .listByBizEntityEnName(record.getBizLineId(), record.getBizEntityEnName()));
    }

    /**
     * 创建扩展属性
     *
     * @param currentUserId 登录用户ID
     * @param record 扩展属性实体
     */
    @WriteAuth
    public void create(Long currentUserId, ExtPropertySimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        extPropertyDomainService.create(currentUserId, ExtPropertySimpleRpo2EntityConverter.INSTANCE.toEntity(record));
    }

    /**
     * 更新扩展属性
     *
     * @param currentUserId 登录用户ID
     * @param record 扩展属性实体
     */
    @WriteAuth
    public void edit(Long currentUserId, ExtPropertySimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        extPropertyDomainService.edit(currentUserId, ExtPropertySimpleRpo2EntityConverter.INSTANCE.toEntity(record));
    }


    /**
     * 删除扩展属性
     *
     * @param currentUserId 登录用户ID
     * @param record 记录数据
     */
    @WriteAuth
    public void delete(Long currentUserId, ExtPropertySimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        extPropertyDomainService.delete(currentUserId, record.getBizLineId(), record.getBizEntityId(), record.getId());
    }

}
