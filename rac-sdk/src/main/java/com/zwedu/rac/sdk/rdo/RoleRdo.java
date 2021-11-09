package com.zwedu.rac.sdk.rdo;

import java.util.List;
import java.util.Map;

/**
 * 角色对象
 *
 * @author qingchuan
 * @date 2020/12/21
 */
public class RoleRdo {
    /**
     * 角色ID
     */
    private Long id;
    /**
     * 角色英文名
     */
    private String enName;
    /**
     * 角色中文名
     */
    private String cnName;
    /**
     * 角色对应的功能访问控制集合
     */
    private Map<FuncRdo, StrategyRdo> funcStrategyMap;
    /**
     * 菜单列表
     */
    private List<MenuRdo> menuList;

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

    public Map<FuncRdo, StrategyRdo> getFuncStrategyMap() {
        return funcStrategyMap;
    }

    public void setFuncStrategyMap(Map<FuncRdo, StrategyRdo> funcStrategyMap) {
        this.funcStrategyMap = funcStrategyMap;
    }

    public List<MenuRdo> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuRdo> menuList) {
        this.menuList = menuList;
    }
}
