package org.poseibon.rac.rowauth.strategy;

import org.poseibon.rac.rowauth.strategy.vo.StrategyInfo;

/**
 * 策略类型解析器
 *
 * @author qingchuan
 * @date 2021/1/3
 */
public interface StrategyTypeParser {
    /**
     * 策略表达式解析方法
     *
     * @return 策略表达式信息
     */
    StrategyInfo parse(String expression);
}
