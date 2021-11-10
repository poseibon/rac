package com.zwedu.rac.application.service;

import com.zwedu.rac.application.converter.StrategyEntity2ComplexRdoConverter;
import com.zwedu.rac.application.converter.StrategyEntity2SimpleRdoConverter;
import com.zwedu.rac.application.converter.StrategySimpleRpo2EntityConverter;
import com.zwedu.rac.domain.entity.StrategyEntity;
import com.zwedu.rac.domain.service.StrategyDomainService;
import com.zwedu.rac.rowauth.annotation.WriteAuth;
import com.zwedu.rac.sdk.rpo.base.PaginationRdo;
import com.zwedu.rac.sdk.rdo.base.PaginationRpo;
import com.zwedu.rac.sdk.rpo.strategy.StrategyComplexRpo;
import com.zwedu.rac.sdk.rpo.strategy.StrategySimpleRpo;
import org.poseibon.common.page.Pagination;
import org.poseibon.common.validator.ParamAssert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 访问策略应用层服务
 *
 * @author qingchuan
 * @date 2020/12/10
 */
@Service
public class StrategyAppService {
    @Resource
    private StrategyDomainService strategyDomainService;

    /**
     * 查询访问策略列表数据
     *
     * @param record 分页查询参数
     * @return 访问策略列表数据
     */
    public PaginationRdo<StrategyComplexRpo> listPage(PaginationRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        // 查询对应的访问策略列表
        Pagination<StrategyEntity> pagination = strategyDomainService.listPage(record.getPageNo(),
                record.getPageSize(), record.getBizLineId(), record.getSearchVal());
        return StrategyEntity2ComplexRdoConverter.INSTANCE.toPaginationRdo(pagination);
    }

    /**
     * 查询授权的访问策略列表
     *
     * @return 访问策略列表数据
     */
    public List<StrategySimpleRpo> listByBizLineId(StrategySimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        return StrategyEntity2SimpleRdoConverter.INSTANCE.toRdoList(strategyDomainService
                .listByBizLineId(record.getBizLineId()));
    }

    /**
     * 创建访问策略
     *
     * @param currentUserId 登录用户ID
     * @param record 访问策略实体
     */
    @WriteAuth
    public void create(Long currentUserId, StrategySimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        strategyDomainService.create(currentUserId, StrategySimpleRpo2EntityConverter.INSTANCE.toEntity(record));
    }

    /**
     * 更新访问策略
     *
     * @param currentUserId 登录用户ID
     * @param record 访问策略实体
     */
    @WriteAuth
    public void edit(Long currentUserId, StrategySimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        strategyDomainService.edit(currentUserId, StrategySimpleRpo2EntityConverter.INSTANCE.toEntity(record));
    }


    /**
     * 删除访问策略
     *
     * @param currentUserId 登录用户ID
     * @param record 记录数据
     */
    @WriteAuth
    public void delete(Long currentUserId, StrategySimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        strategyDomainService.delete(currentUserId, record.getBizLineId(), record.getId());
    }


}
