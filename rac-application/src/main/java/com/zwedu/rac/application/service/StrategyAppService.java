package com.zwedu.rac.application.service;

import com.zwedu.rac.application.converter.StrategyEntity2ComplexDtoConverter;
import com.zwedu.rac.application.converter.StrategyEntity2SimpleDtoConverter;
import com.zwedu.rac.application.converter.StrategySimpleDto2EntityConverter;
import com.zwedu.rac.sdk.rpo.base.ReqPaginationRpo;
import com.zwedu.rac.sdk.rpo.base.ResPaginationRpo;
import com.zwedu.rac.sdk.rpo.strategy.StrategyComplexRpo;
import com.zwedu.rac.sdk.rpo.strategy.StrategySimpleRpo;
import com.zwedu.rac.common.annotation.WriteAuth;
import org.poseibon.common.page.Pagination;
import org.poseibon.common.validator.ParamAssert;
import com.zwedu.rac.domain.entity.StrategyEntity;
import com.zwedu.rac.domain.service.StrategyDomainService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class StrategyAppService {
    @Resource
    private StrategyDomainService strategyDomainService;

    /**
     * 查询访问策略列表数据
     *
     * @param record 分页查询参数
     * @return 访问策略列表数据
     */
    public ResPaginationRpo<StrategyComplexRpo> listPage(ReqPaginationRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        // 查询对应的访问策略列表
        Pagination<StrategyEntity> pagination = strategyDomainService.listPage(record.getPageNo(),
                record.getPageSize(), record.getBizLineId(), record.getSearchVal());
        return StrategyEntity2ComplexDtoConverter.INSTANCE.toPaginationDto(pagination);
    }

    /**
     * 查询授权的访问策略列表
     *
     * @return 访问策略列表数据
     */
    public List<StrategySimpleRpo> listByBizLineId(StrategySimpleRpo record) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(record);
        return StrategyEntity2SimpleDtoConverter.INSTANCE.toDtoList(strategyDomainService
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
        strategyDomainService.create(currentUserId, StrategySimpleDto2EntityConverter.INSTANCE.toEntity(record));
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
        strategyDomainService.edit(currentUserId, StrategySimpleDto2EntityConverter.INSTANCE.toEntity(record));
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
