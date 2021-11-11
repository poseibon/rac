package org.poseibon.rac.sdk.rpo.dimension;

import org.poseibon.rac.sdk.rpo.base.BaseSimpleRpo;

/**
 * 维度传输对象
 *
 * @author qingchuan
 * @date 2020/12/9
 */
public class DimensionSimpleRpo extends BaseSimpleRpo {
    /**
     * 维度节点类型ID
     */
    private Long nodeTypeId;

    /**
     * 是否使用扩展属性
     */
    private Integer useExtProperty;

    public Integer getUseExtProperty() {
        return useExtProperty;
    }

    public void setUseExtProperty(Integer useExtProperty) {
        this.useExtProperty = useExtProperty;
    }

    public Long getNodeTypeId() {
        return nodeTypeId;
    }

    public void setNodeTypeId(Long nodeTypeId) {
        this.nodeTypeId = nodeTypeId;
    }
}
