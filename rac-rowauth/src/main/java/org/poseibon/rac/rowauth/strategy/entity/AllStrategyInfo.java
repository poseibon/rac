package org.poseibon.rac.rowauth.strategy.entity;

import org.poseibon.rac.rowauth.enums.StrategyTypeEnum;

/**
 * 所有数据策略信息
 *
 * @author qingchuan
 * @date 2021/1/3
 */
public class AllStrategyInfo extends StrategyInfo {
    /**
     * constructor
     */
    public AllStrategyInfo() {
        this.setType(StrategyTypeEnum.ALL.getText());
    }
}
