package com.zwedu.rac.interfaces.controller;

import com.zwedu.rac.application.service.DictionaryNodeAppService;
import com.zwedu.rac.sdk.rpo.dictionary.DictionaryNodeSimpleRpo;
import com.zwedu.rac.sdk.rpo.ext.ExtDataSimpleRpo;
import com.zwedu.rac.shiro.utils.SessionHelper;
import org.poseibon.common.utils.BaseResponse;
import org.poseibon.common.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 字典节点控制器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Controller
@RequestMapping("/dictionary/node")
public class DictionaryNodeController {
    @Resource
    private DictionaryNodeAppService dictionaryNodeAppService;

    /**
     * 查询字典节点列表
     *
     * @param record 字典分页数据
     * @return 字典节点列表数据
     */
    @RequestMapping("/listByParentId")
    @ResponseBody
    public BaseResponse listByParentId(@RequestBody DictionaryNodeSimpleRpo record) {
        return ResponseUtil.success(dictionaryNodeAppService.listByParentId(record));
    }


    /**
     * 查询字典节点列表
     *
     * @param record 字典分页数据
     * @return 字典节点列表数据
     */
    @RequestMapping("/listByDictionaryId")
    @ResponseBody
    public BaseResponse listByDictionaryId(
            @RequestBody DictionaryNodeSimpleRpo record) {
        return ResponseUtil.success(dictionaryNodeAppService.listByDictionaryId(record));
    }


    /**
     * 查询维度的扩展属性
     *
     * @param record 用户扩展属性数据
     */
    @RequestMapping("/listExtProperty")
    @ResponseBody
    public BaseResponse listExtProperty(@RequestBody DictionaryNodeSimpleRpo record) {
        return ResponseUtil.success(dictionaryNodeAppService.listExtProperty(record));
    }



    /**
     * 添加用户扩展属性
     *
     * @param record 用户扩展属性数据
     */
    @RequestMapping("/addExtProperty")
    @ResponseBody
    public BaseResponse addExtProperty(@RequestBody ExtDataSimpleRpo record) {
        Long currentLoginId = SessionHelper.getLoginUserId();
        dictionaryNodeAppService.addExtProperty(currentLoginId, record);
        return ResponseUtil.success();
    }


    /**
     * 删除用户扩展属性
     *
     * @param record 用户扩展属性数据
     */
    @RequestMapping("/dropExtProperty")
    @ResponseBody
    public BaseResponse dropExtProperty(@RequestBody ExtDataSimpleRpo record) {
        dictionaryNodeAppService.dropExtProperty(record);
        return ResponseUtil.success();
    }

    /**
     * 创建字典节点
     *
     * @param record 字典节点数据
     */
    @RequestMapping("/create")
    @ResponseBody
    public BaseResponse create(@RequestBody DictionaryNodeSimpleRpo record) {
        Long currentLoginId = SessionHelper.getLoginUserId();
        dictionaryNodeAppService.create(currentLoginId, record);
        return ResponseUtil.success();
    }



    /**
     * 更新字典节点
     *
     * @param record 字典节点数据
     */
    @RequestMapping("/edit")
    @ResponseBody
    public BaseResponse edit(@RequestBody DictionaryNodeSimpleRpo record) {
        Long currentLoginId = SessionHelper.getLoginUserId();
        dictionaryNodeAppService.edit(currentLoginId, record);
        return ResponseUtil.success();
    }


    /**
     * 删除字典节点
     *
     * @param record 字典节点
     */
    @RequestMapping("/delete")
    @ResponseBody
    public BaseResponse delete(@RequestBody DictionaryNodeSimpleRpo record) {
        Long currentLoginId = SessionHelper.getLoginUserId();
        dictionaryNodeAppService.delete(currentLoginId, record);
        return ResponseUtil.success();
    }
}
