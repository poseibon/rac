package com.zwedu.rac.interfaces.controller;

import com.zwedu.rac.sdk.rpo.base.ReqPaginationRpo;
import com.zwedu.rac.sdk.rpo.strategy.StrategySimpleRpo;
import com.zwedu.rac.application.service.StrategyAppService;
import org.poseibon.common.utils.BaseResponse;
import org.poseibon.common.utils.ResponseUtil;
import com.zwedu.rac.interfaces.common.session.SessionHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 策略控制器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Controller
@Slf4j
@RequestMapping("/strategy")
public class StrategyController {
    @Resource
    private StrategyAppService strategyAppService;

    /**
     * 查询可用的策略列表
     *
     * @param paginationDto 分页查询参数
     * @return 策略列表数据
     */
    @RequestMapping("/listPage")
    @ResponseBody
    public BaseResponse listPage(@RequestBody ReqPaginationRpo paginationDto) {
        return ResponseUtil.success(strategyAppService.listPage(paginationDto));
    }


    /**
     * 查询策略列表
     *
     * @return 策略列表数据
     */
    @RequestMapping("/listByBizLineId")
    @ResponseBody
    public BaseResponse listByBizLineId(@RequestBody StrategySimpleRpo record) {
        return ResponseUtil.success(strategyAppService.listByBizLineId(record));
    }

    /**
     * 创建策略
     *
     * @param record 策略数据
     */
    @RequestMapping("/create")
    @ResponseBody
    public BaseResponse create(@RequestBody StrategySimpleRpo record) {
        Long currentLoginId = SessionHelper.getLoginUserId();
        strategyAppService.create(currentLoginId, record);
        return ResponseUtil.success();
    }


    /**
     * 更新策略
     *
     * @param record 策略数据
     */
    @RequestMapping("/edit")
    @ResponseBody
    public BaseResponse edit(@RequestBody StrategySimpleRpo record) {
        Long currentLoginId = SessionHelper.getLoginUserId();
        strategyAppService.edit(currentLoginId, record);
        return ResponseUtil.success();
    }


    /**
     * 删除策略
     *
     * @param record 策略
     */
    @RequestMapping("/delete")
    @ResponseBody
    public BaseResponse delete(@RequestBody StrategySimpleRpo record) {
        Long currentLoginId = SessionHelper.getLoginUserId();
        strategyAppService.delete(currentLoginId, record);
        return ResponseUtil.success();
    }
}
