package org.poseibon.rac.sdk.rdo.dictionary;

import org.poseibon.rac.sdk.rdo.base.BaseComplexRdo;

/**
 * 字典节点传输对象
 *
 * @author qingchuan
 * @date 2020/12/9
 */
public class DictionaryNodeComplexRdo extends BaseComplexRdo<DictionaryNodeComplexRdo> {
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
