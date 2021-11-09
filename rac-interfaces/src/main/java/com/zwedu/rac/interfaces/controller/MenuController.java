package com.zwedu.rac.interfaces.controller;

import com.zwedu.rac.sdk.rpo.menu.MenuSimpleRpo;
import com.zwedu.rac.application.service.MenuAppService;
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
 * 菜单控制器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Controller
@Slf4j
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private MenuAppService menuAppService;

    /**
     * 查询可用的菜单列表
     *
     * @param record 查询参数
     * @return 菜单列表数据
     */
    @RequestMapping("/listByParentId")
    @ResponseBody
    public BaseResponse listByParentId(@RequestBody MenuSimpleRpo record) {
        return ResponseUtil.success(menuAppService.listByParentId(record));
    }

    /**
     * 查询可用的菜单列表
     *
     * @param record 查询参数
     * @return 菜单列表数据
     */
    @RequestMapping("/listByBizLineId")
    @ResponseBody
    public BaseResponse listByBizLineId(@RequestBody MenuSimpleRpo record) {
        return ResponseUtil.success(menuAppService.listByBizLineId(record));
    }

    /**
     * 创建菜单
     *
     * @param record 菜单数据
     */
    @RequestMapping("/create")
    @ResponseBody
    public BaseResponse create(@RequestBody MenuSimpleRpo record) {
        Long currentLoginId = SessionHelper.getLoginUserId();
        menuAppService.create(currentLoginId, record);
        return ResponseUtil.success();
    }


    /**
     * 编辑菜单
     *
     * @param record 菜单数据
     */
    @RequestMapping("/edit")
    @ResponseBody
    public BaseResponse edit(@RequestBody MenuSimpleRpo record) {
        Long currentLoginId = SessionHelper.getLoginUserId();
        menuAppService.edit(currentLoginId, record);
        return ResponseUtil.success();
    }


    /**
     * 删除菜单
     *
     * @param record 菜单数据
     */
    @RequestMapping("/delete")
    @ResponseBody
    public BaseResponse delete(@RequestBody MenuSimpleRpo record) {
        Long currentLoginId = SessionHelper.getLoginUserId();
        menuAppService.delete(currentLoginId, record);
        return ResponseUtil.success();
    }
}
