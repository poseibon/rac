package com.zwedu.rac.interfaces.controller;

import com.zwedu.rac.sdk.rpo.base.ReqPaginationRpo;
import com.zwedu.rac.sdk.rpo.ext.ExtDataSimpleDto;
import com.zwedu.rac.sdk.rpo.user.UserPermitRpo;
import com.zwedu.rac.sdk.rdo.user.UserSimpleRdo;
import com.zwedu.rac.application.service.AuthAppService;
import com.zwedu.rac.application.service.UserAppService;
import org.poseibon.common.utils.BaseResponse;
import org.poseibon.common.utils.ResponseUtil;
import com.zwedu.rac.shiro.utils.SessionHelper;
import com.zwedu.rac.sdk.rpo.menu.MenuRpo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 用户控制器
 *
 * @author qingchuan
 * @date 2020/12/10
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserAppService userAppService;
    @Resource
    private AuthAppService authAppService;

    /**
     * 查询未删除的用户列表
     *
     * @param paginationDto 分页查询参数
     * @return 用户列表数据
     */
    @RequestMapping("/listPage")
    @ResponseBody
    public BaseResponse listPage(@RequestBody ReqPaginationRpo paginationDto) {
        Long currentLoginId = SessionHelper.getLoginUserId();
        return ResponseUtil.success(userAppService.listPage(currentLoginId, paginationDto));
    }


    /**
     * 查询用户授权的角色ID
     *
     * @param record 用户角色数据
     */
    @RequestMapping("/listUserRoleId")
    @ResponseBody
    public BaseResponse listUserRoleId(@RequestBody UserPermitRpo record) {
        return ResponseUtil.success(userAppService.listUserRoleId(record));
    }


    /**
     * 查询用户授权的维度节点ID
     *
     * @param record 用户角色数据
     */
    @RequestMapping("/listUserDimensionNodes")
    @ResponseBody
    public BaseResponse listUserDimensionNodes(@RequestBody UserPermitRpo record) {
        return ResponseUtil.success(userAppService.listUserDimensionNodes(record));
    }

    /**
     * 查询用户授权的扩展属性
     *
     * @param record 用户扩展属性数据
     */
    @RequestMapping("/listUserExtProperty")
    @ResponseBody
    public BaseResponse listUserExtProperty(@RequestBody UserPermitRpo record) {
        return ResponseUtil.success(userAppService.listUserExtProperty(record));
    }


    /**
     * 查询用户授权的扩展属性
     */
    @RequestMapping("/listMenu")
    @ResponseBody
    public BaseResponse listMenu() {
        return ResponseUtil.success(authAppService.listMenu(MenuRpo.of(SessionHelper.getLoginUserId(),
                SessionHelper.getBizLineId())));
    }

    /**
     * 给用户授权
     *
     * @param record 用户授权数据
     */
    @RequestMapping("/grantRoles")
    @ResponseBody
    public BaseResponse grantRoles(@RequestBody UserPermitRpo record) {
        Long currentLoginId = SessionHelper.getLoginUserId();
        userAppService.grantRoles(currentLoginId, record);
        return ResponseUtil.success();
    }

    /**
     * 给用户授权
     *
     * @param record 用户授权数据
     */
    @RequestMapping("/grantDimensionNodes")
    @ResponseBody
    public BaseResponse grantDimensionNodes(@RequestBody UserPermitRpo record) {
        Long currentLoginId = SessionHelper.getLoginUserId();
        userAppService.grantDimensionNodes(currentLoginId, record);
        return ResponseUtil.success();
    }

    /**
     * 创建用户
     *
     * @param record 用户数据
     */
    @RequestMapping("/create")
    @ResponseBody
    public BaseResponse create(@RequestBody UserSimpleRdo record) {
        Long currentLoginId = SessionHelper.getLoginUserId();
        userAppService.create(currentLoginId, record);
        return ResponseUtil.success();
    }


    /**
     * 添加用户扩展属性
     *
     * @param record 用户扩展属性数据
     */
    @RequestMapping("/addExtProperty")
    @ResponseBody
    public BaseResponse addExtProperty(@RequestBody ExtDataSimpleDto record) {
        Long currentLoginId = SessionHelper.getLoginUserId();
        userAppService.addExtProperty(currentLoginId, record);
        return ResponseUtil.success();
    }


    /**
     * 删除用户扩展属性
     *
     * @param record 用户扩展属性数据
     */
    @RequestMapping("/dropExtProperty")
    @ResponseBody
    public BaseResponse dropExtProperty(@RequestBody ExtDataSimpleDto record) {
        userAppService.dropExtProperty(record);
        return ResponseUtil.success();
    }

    /**
     * 更新用户
     *
     * @param record 用户数据
     */
    @RequestMapping("/edit")
    @ResponseBody
    public BaseResponse edit(@RequestBody UserSimpleRdo record) {
        Long currentLoginId = SessionHelper.getLoginUserId();
        userAppService.edit(currentLoginId, record);
        return ResponseUtil.success();
    }


    /**
     * 删除用户
     *
     * @param record 用户数据
     */
    @RequestMapping("/delete")
    @ResponseBody
    public BaseResponse delete(@RequestBody UserSimpleRdo record) {
        Long currentLoginId = SessionHelper.getLoginUserId();
        userAppService.delete(currentLoginId, record);
        return ResponseUtil.success();
    }
}
