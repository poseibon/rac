package com.zwedu.rac.sdk.vo;

import com.zwedu.rac.sdk.rdo.StrategyRdo;
import com.zwedu.rac.sdk.rdo.UserRdo;

import java.io.Serializable;
import java.util.Map;

/**
 * 用户session
 *
 * @author qingchuan
 * @date 2020/12/24
 */
public class UserSession implements Serializable {
    /**
     * 用户信息
     */
    private UserRdo userRdo;
    /**
     * 功能访问控制集合
     */
    private Map<String, StrategyRdo> funcStrategyMap;

    public static UserSession builder() {
        UserSession userSession = new UserSession();
        return userSession;
    }

    public UserRdo getUserRdo() {
        return userRdo;
    }

    public Map<String, StrategyRdo> getFuncStrategyMap() {
        return funcStrategyMap;
    }

    public UserSession userRdo(UserRdo userRdo) {
        this.userRdo = userRdo;
        return this;
    }

    public UserSession funcStrategyMap(Map<String, StrategyRdo> funcStrategyMap) {
        this.funcStrategyMap = funcStrategyMap;
        return this;
    }
}
