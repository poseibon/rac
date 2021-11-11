package com.zwedu.rac.sdk.rpo.dictionary;

import com.zwedu.rac.sdk.rpo.base.BaseSimpleRpo;

/**
 * 字典传输对象
 *
 * @author qingchuan
 * @date 2020/12/9
 */
public class DictionarySimpleRpo extends BaseSimpleRpo {

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
