package org.poseibon.rac.sdk.rdo.menu;


import org.poseibon.common.tree.TreeNode;

import java.io.Serializable;

/**
 * 菜单对象
 *
 * @author qingchuan
 * @date 2020/12/18
 */
public class MenuRdo extends TreeNode<MenuRdo> implements Serializable {
    /**
     * 英文名
     */
    private String enName;
    /**
     * 中文名
     */
    private String cnName;
    /**
     * url
     */
    private String url;

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }
}
