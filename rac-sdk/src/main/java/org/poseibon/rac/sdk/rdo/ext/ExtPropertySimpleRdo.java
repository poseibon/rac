package org.poseibon.rac.sdk.rdo.ext;

import org.poseibon.rac.sdk.rdo.base.BaseSimpleRdo;

/**
 * 扩展属性传输对象
 *
 * @author qingchuan
 * @date 2020/12/9
 */
public class ExtPropertySimpleRdo extends BaseSimpleRdo {
    /**
     * 业务实体ID
     */
    private Long bizEntityId;
    /**
     * 字典类型
     */
    private Integer type;
    /**
     * 业务实体ID
     */
    private String defaultValue;
    /**
     * 业务实体ID
     */
    private Long dictionaryId;
    /**
     * 业务实体英文名
     */
    private String bizEntityEnName;

    public Long getBizEntityId() {
        return bizEntityId;
    }

    public void setBizEntityId(Long bizEntityId) {
        this.bizEntityId = bizEntityId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Long getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(Long dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public String getBizEntityEnName() {
        return bizEntityEnName;
    }

    public void setBizEntityEnName(String bizEntityEnName) {
        this.bizEntityEnName = bizEntityEnName;
    }
}
