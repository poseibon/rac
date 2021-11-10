package com.zwedu.rac.interfaces.controller;

import com.zwedu.rac.application.service.BizEntityAppService;
import com.zwedu.rac.sdk.rpo.base.ReqPaginationRpo;
import com.zwedu.rac.sdk.rpo.bizentity.BizEntitySimpleRpo;
import com.zwedu.rac.shiro.utils.SessionHelper;
import org.poseibon.common.utils.BaseResponse;
import org.poseibon.common.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 业务实体控制器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Controller
@RequestMapping("/bizentity")
public class BizEntityController {
    @Resource
    private BizEntityAppService bizEntityAppService;

    /**
     * 查询可用的业务实体列表
     *
     * @param paginationDto 分页查询参数
     * @return 业务实体列表数据
     */
    @RequestMapping("/listPage")
    @ResponseBody
    public BaseResponse listPage(@RequestBody ReqPaginationRpo paginationDto) {
        return ResponseUtil.success(bizEntityAppService.listPage(paginationDto));
    }


    /**
     * 查询业务实体列表
     *
     * @return 业务实体列表数据
     */
    @RequestMapping("/listByBizLineId")
    @ResponseBody
    public BaseResponse listByBizLineId(@RequestBody BizEntitySimpleRpo record) {
        return ResponseUtil.success(bizEntityAppService.listByBizLineId(record));
    }

    /**
     * 创建业务实体
     *
     * @param record 业务实体数据
     */
    @RequestMapping("/create")
    @ResponseBody
    public BaseResponse create(@RequestBody BizEntitySimpleRpo record) {
        Long currentLoginId = SessionHelper.getLoginUserId();
        bizEntityAppService.create(currentLoginId, record);
        return ResponseUtil.success();
    }


    /**
     * 更新业务实体
     *
     * @param record 业务实体数据
     */
    @RequestMapping("/edit")
    @ResponseBody
    public BaseResponse edit(@RequestBody BizEntitySimpleRpo record) {
        Long currentLoginId = SessionHelper.getLoginUserId();
        bizEntityAppService.edit(currentLoginId, record);
        return ResponseUtil.success();
    }


    /**
     * 删除业务实体
     *
     * @param record 业务实体
     */
    @RequestMapping("/delete")
    @ResponseBody
    public BaseResponse delete(@RequestBody BizEntitySimpleRpo record) {
        Long currentLoginId = SessionHelper.getLoginUserId();
        bizEntityAppService.delete(currentLoginId, record);
        return ResponseUtil.success();
    }
}
