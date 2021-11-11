package org.poseibon.rac.interfaces.controller;

import org.poseibon.rac.application.service.FuncAppService;
import org.poseibon.rac.sdk.rpo.func.FuncSimpleRpo;
import org.poseibon.rac.shiro.utils.SessionHelper;
import org.poseibon.common.utils.BaseResponse;
import org.poseibon.common.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 功能控制器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Controller
@RequestMapping("/func")
public class FuncController {
    @Resource
    private FuncAppService funcAppService;


    /**
     * 查询可用的功能列表
     *
     * @param record 查询参数
     * @return 功能列表数据
     */
    @RequestMapping("/listByParentId")
    @ResponseBody
    public BaseResponse listByParentId(@RequestBody FuncSimpleRpo record) {
        return ResponseUtil.success(funcAppService.listByParentId(record));
    }


    /**
     * 查询可用的功能列表
     *
     * @param record 查询参数
     * @return 功能列表数据
     */
    @RequestMapping("/listByBizLineId")
    @ResponseBody
    public BaseResponse listByBizLineId(@RequestBody FuncSimpleRpo record) {
        return ResponseUtil.success(funcAppService.listByBizLineId(record));
    }

    /**
     * 创建功能
     *
     * @param record 功能数据
     */
    @RequestMapping("/create")
    @ResponseBody
    public BaseResponse create(@RequestBody FuncSimpleRpo record) {
        Long currentLoginId = SessionHelper.getLoginUserId();
        funcAppService.create(currentLoginId, record);
        return ResponseUtil.success();
    }


    /**
     * 编辑功能
     *
     * @param record 功能数据
     */
    @RequestMapping("/edit")
    @ResponseBody
    public BaseResponse edit(@RequestBody FuncSimpleRpo record) {
        Long currentLoginId = SessionHelper.getLoginUserId();
        funcAppService.edit(currentLoginId, record);
        return ResponseUtil.success();
    }


    /**
     * 删除功能
     *
     * @param record 功能数据
     */
    @RequestMapping("/delete")
    @ResponseBody
    public BaseResponse delete(@RequestBody FuncSimpleRpo record) {
        Long currentLoginId = SessionHelper.getLoginUserId();
        funcAppService.delete(currentLoginId, record);
        return ResponseUtil.success();
    }
}
