package com.zwedu.rac.common.strategy.entity;

import com.zwedu.rac.common.enums.StrategyTypeEnum;

/**
 * 维度策略信息
 *
 * @author qingchuan
 * @date 2021/1/3
 */
public class DimensionStrategyInfo extends StrategyInfo {
    /**
     * 维度
     */
    public DimensionStrategyInfo() {
        this.setType(StrategyTypeEnum.DIMENSION.getText());
    }
}
