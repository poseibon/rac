package com.zwedu.rac.sdk.rpo.strategy;

import com.zwedu.rac.sdk.rpo.base.BaseSimpleRpo;

/**
 * 访问策略传输对象
 *
 * @author qingchuan
 * @date 2020/12/9
 */
public class StrategySimpleRpo extends BaseSimpleRpo {
    /**
     * 类型
     */
    private Integer type;
    /**
     * 表达式
     */
    private String expression;
    /**
     * 优先级
     */
    private Integer priority;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
