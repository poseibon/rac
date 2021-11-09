package com.zwedu.rac.common.strategy.entity;

import com.zwedu.rac.common.enums.StrategyTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * 固定值属性信息
 *
 * @author qingchuan
 * @date 2021/1/3
 */
public class FixStrategyInfo extends StrategyInfo {
    /**
     * 授权列表
     */
    private List<String> authList;

    /**
     * constructor
     */
    public FixStrategyInfo() {
        this.setType(StrategyTypeEnum.FIX.getText());
        this.authList = new ArrayList<>();
    }

    /**
     * constructor
     */
    public FixStrategyInfo(List<String> authList) {
        this.setType(StrategyTypeEnum.FIX.getText());
        this.authList = authList;
    }

    public List<String> getAuthList() {
        return authList;
    }

    public void setAuthList(List<String> authList) {
        this.authList = authList;
    }
}
