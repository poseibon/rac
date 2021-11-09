package com.zwedu.rac.sdk.rdo;

import java.io.Serializable;
import java.util.List;

/**
 * 业务线
 *
 * @author qingchuan
 * @date 2020/12/21
 */
public class BizLineRdo implements Serializable {
    /**
     * 业务线ID
     */
    private Long id;
    /**
     * 英文名
     */
    private String enName;
    /**
     * 中文名
     */
    private String cnName;
    /**
     * 角色列表
     */
    private List<RoleRdo> roleList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public List<RoleRdo> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleRdo> roleList) {
        this.roleList = roleList;
    }
}
