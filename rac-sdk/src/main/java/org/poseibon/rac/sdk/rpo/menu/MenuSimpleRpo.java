package org.poseibon.rac.sdk.rpo.menu;

import org.poseibon.rac.sdk.rdo.base.BaseSimpleRdo;

/**
 * 菜单传输对象
 *
 * @author qingchuan
 * @date 2020/12/9
 */
public class MenuSimpleRpo extends BaseSimpleRdo {
    /**
     * 菜单URL
     */
    private String url;
    /**
     * 序号
     */
    private Integer seq;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}
