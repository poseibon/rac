package com.zwedu.rac.sdk.rdo.ext;

import com.zwedu.rac.sdk.rdo.base.BaseComplexRdo;

/**
 * 扩展属性传输对象
 *
 * @author qingchuan
 * @date 2020/12/9
 */
public class ExtPropertyComplexRdo extends BaseComplexRdo {
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
}
