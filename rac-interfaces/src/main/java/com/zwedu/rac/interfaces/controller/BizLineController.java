package com.zwedu.rac.interfaces.controller;

import com.zwedu.rac.application.service.BizLineAppService;
import com.zwedu.rac.sdk.rpo.base.ReqPaginationRpo;
import com.zwedu.rac.sdk.rpo.bizline.BizLineSimpleRpo;
import com.zwedu.rac.shiro.utils.SessionHelper;
import org.poseibon.common.utils.BaseResponse;
import org.poseibon.common.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 业务线控制器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Controller
@RequestMapping("/bizline")
public class BizLineController {
    @Resource
    private BizLineAppService bizLineAppService;

    /**
     * 查询可用的业务线列表
     *
     * @param paginationDto 分页查询参数
     * @return 业务线列表数据
     */
    @RequestMapping("/listPage")
    @ResponseBody
    public BaseResponse listPage(@RequestBody ReqPaginationRpo paginationDto) {
        return ResponseUtil.success(bizLineAppService.listPage(paginationDto));
    }


    /**
     * 查询授权的业务线列表
     *
     * @return 业务线列表数据
     */
    @RequestMapping("/listAuthBizLine")
    @ResponseBody
    public BaseResponse listAuthBizLine() {
        return ResponseUtil.success(bizLineAppService.listAuthBizLine());
    }

    /**
     * 创建业务线
     *
     * @param record 业务线数据
     */
    @RequestMapping("/create")
    @ResponseBody
    public BaseResponse create(@RequestBody BizLineSimpleRpo record) {
        Long currentLoginId = SessionHelper.getLoginUserId();
        bizLineAppService.create(currentLoginId, record);
        return ResponseUtil.success();
    }


    /**
     * 更新业务线
     *
     * @param record 业务线数据
     */
    @RequestMapping("/edit")
    @ResponseBody
    public BaseResponse edit(@RequestBody BizLineSimpleRpo record) {
        Long currentLoginId = SessionHelper.getLoginUserId();
        bizLineAppService.edit(currentLoginId, record);
        return ResponseUtil.success();
    }


    /**
     * 删除业务线
     *
     * @param record 业务线
     */
    @RequestMapping("/delete")
    @ResponseBody
    public BaseResponse delete(@RequestBody BizLineSimpleRpo record) {
        Long currentLoginId = SessionHelper.getLoginUserId();
        bizLineAppService.delete(currentLoginId, record);
        return ResponseUtil.success();
    }
}
