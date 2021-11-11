package org.poseibon.rac.sdk.rdo.menu;

import org.poseibon.rac.sdk.rdo.base.BaseComplexRdo;

/**
 * 菜单传输对象
 *
 * @author qingchuan
 * @date 2020/12/9
 */
public class MenuComplexRdo extends BaseComplexRdo<MenuComplexRdo> {
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
