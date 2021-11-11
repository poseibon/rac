package org.poseibon.rac.domain.entity;

import org.poseibon.rac.domain.common.validator.BizAssert;
import org.poseibon.rac.domain.entity.base.BaseEntity;

/**
 * 策略实体
 *
 * @author qingchuan
 * @date 2020/12/21
 */
public class StrategyEntity extends BaseEntity {
    /**
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

    @Override
    public void validate() {
    }

    @Override
    public void create(Long currentUserId) {
        validate();
        completeForCreate(this, currentUserId);
    }


    @Override
    public void edit(Long currentUserId) {
        validate();
        completeForUpdate(this, currentUserId);
    }


    @Override
    public void delete(Long currentUserId) {
        validate();
        completeForDelete(this, currentUserId);
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        BizAssert.INCORRECT_VALUE_ERROR.notNull(type, "类型");
        this.type = type;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        BizAssert.PARAM_EMPTY_ERROR.notNull(expression, "表达式");
        this.expression = expression;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        BizAssert.PARAM_EMPTY_ERROR.notNull(priority, "优先级");
        this.priority = priority;
    }
}
