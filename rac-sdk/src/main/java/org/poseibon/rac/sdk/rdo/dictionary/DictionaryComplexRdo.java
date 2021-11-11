package org.poseibon.rac.sdk.rdo.dictionary;

import org.poseibon.rac.sdk.rdo.base.BaseComplexRdo;

/**
 * 字典传输对象
 *
 * @author qingchuan
 * @date 2020/12/9
 */
public class DictionaryComplexRdo extends BaseComplexRdo {

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
}
