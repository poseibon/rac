package com.zwedu.rac.sdk.rpo.dictionary;

import com.zwedu.rac.sdk.rpo.base.BaseSimpleRpo;

/**
 * 字典节点传输对象
 *
 * @author qingchuan
 * @date 2020/12/9
 */
public class DictionaryNodeSimpleRpo extends BaseSimpleRpo {
    /**
     * 值
     */
    private String value;
    /**
     * 字典ID
     */
    private Long dictionaryId;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(Long dictionaryId) {
        this.dictionaryId = dictionaryId;
    }
}
