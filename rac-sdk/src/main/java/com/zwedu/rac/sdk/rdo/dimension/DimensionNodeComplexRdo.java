package com.zwedu.rac.sdk.rdo.dimension;

import com.zwedu.rac.sdk.rdo.base.BaseComplexRdo;

/**
 * 维度节点传输对象
 *
 * @author qingchuan
 * @date 2020/12/9
 */
public class DimensionNodeComplexRdo extends BaseComplexRdo<DimensionNodeComplexRdo> {
    /**
     * 值
     */
    private Long type;
    /**
     * 维度ID
     */
    private Long dimensionId;
    /**
     * 生效状态
     */
    private Integer status;
    /**
     * 分级管控ID
     */
    private Long decentralizedControlId;

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(Long dimensionId) {
        this.dimensionId = dimensionId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getDecentralizedControlId() {
        return decentralizedControlId;
    }

    public void setDecentralizedControlId(Long decentralizedControlId) {
        this.decentralizedControlId = decentralizedControlId;
    }
}
