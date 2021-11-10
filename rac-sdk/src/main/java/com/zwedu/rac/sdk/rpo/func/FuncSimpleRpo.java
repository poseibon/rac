package com.zwedu.rac.sdk.rpo.func;

import com.zwedu.rac.sdk.rpo.base.BaseSimpleRdo;

/**
 * 功能传输对象
 *
 * @author qingchuan
 * @date 2020/12/9
 */
public class FuncSimpleRpo extends BaseSimpleRdo {
    /**
     * 功能内容
     */
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
