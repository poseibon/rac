package com.zwedu.rac.application.service;

import com.zwedu.rac.application.converter.DimensionEntity2ComplexRdoConverter;
import com.zwedu.rac.application.converter.DimensionEntity2SimpleRdoConverter;
import com.zwedu.rac.application.converter.DimensionSimpleRpo2EntityConverter;
import com.zwedu.rac.domain.entity.DimensionEntity;
import com.zwedu.rac.domain.service.DimensionDomainService;
import com.zwedu.rac.rowauth.annotation.WriteAuth;
import com.zwedu.rac.sdk.rdo.base.PaginationRdo;
import com.zwedu.rac.sdk.rdo.base.PaginationRpo;
import com.zwedu.rac.sdk.rdo.dimension.DimensionSimpleRdo;
import com.zwedu.rac.sdk.rdo.dimension.DimensionComplexRdo;
import com.zwedu.rac.sdk.rpo.dimension.DimensionSimpleRpo;
import org.poseibon.common.page.Pagination;
import org.poseibon.common.validator.ParamAssert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 维度应用层服务
 *
 * @author qingchuan
 * @date 2020/12/10
 */
@Service
public class DimensionAppService {
    @Resource
    private DimensionDomainService dimensionDomainService;

    /**
     * 查询维度列表数据
     *
     * @param record 分页查询参数
     * @return 维度列表数据
     */
    public PaginationRdo<DimensionComplexRdo> listPage(PaginationRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        // 查询对应的维度列表
        Pagination<DimensionEntity> pagination = dimensionDomainService.listPage(record.getPageNo(),
                record.getPageSize(), record.getBizLineId(), record.getSearchVal());
        return DimensionEntity2ComplexRdoConverter.INSTANCE.toPaginationRdo(pagination);
    }

    /**
     * 查询授权的维度列表
     *
     * @return 维度列表数据
     */
    public List<DimensionSimpleRdo> listByBizLineId(DimensionSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        return DimensionEntity2SimpleRdoConverter.INSTANCE.toRdoList(dimensionDomainService
                .listByBizLineId(record.getBizLineId()));
    }

    /**
     * 创建维度
     *
     * @param currentUserId 登录用户ID
     * @param record        维度实体
     */
    @WriteAuth
    public void create(Long currentUserId, DimensionSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        dimensionDomainService.create(currentUserId, DimensionSimpleRpo2EntityConverter.INSTANCE.toEntity(record));
    }

    /**
     * 更新维度
     *
     * @param currentUserId 登录用户ID
     * @param record        维度实体
     */
    @WriteAuth
    public void edit(Long currentUserId, DimensionSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        dimensionDomainService.edit(currentUserId, DimensionSimpleRpo2EntityConverter.INSTANCE.toEntity(record));
    }


    /**
     * 删除维度
     *
     * @param currentUserId 登录用户ID
     * @param record        记录数据
     */
    @WriteAuth
    public void delete(Long currentUserId, DimensionSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        dimensionDomainService.delete(currentUserId, record.getBizLineId(), record.getId());
    }


    /**
     * 根据维度英文名查询维度信息
     *
     * @param record 记录
     * @return 维度信息
     */
    public DimensionSimpleRdo queryByEnName(DimensionSimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        return DimensionEntity2SimpleRdoConverter.INSTANCE.toRdo(dimensionDomainService
                .queryByEnName(record.getBizLineId(), record.getEnName()));
    }
}
