package com.zwedu.rac.sdk.rdo.func;

import com.zwedu.rac.sdk.rdo.base.BaseComplexRdo;

/**
 * 功能传输对象
 *
 * @author qingchuan
 * @date 2020/12/9
 */
public class FuncComplexRdo extends BaseComplexRdo<FuncComplexRdo> {
    /**
     * 功能URL
     */
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
