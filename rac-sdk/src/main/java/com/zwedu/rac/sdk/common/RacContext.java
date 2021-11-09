package com.zwedu.rac.sdk.common;

import com.zwedu.rac.sdk.rdo.UserRdo;

import java.util.Map;

/**
 * 上线文信息
 *
 * @author qingchuan
 * @date 2020/12/24
 */
public class RacContext {
    /**
     * 用户ID
     */
    private UserRdo userRdo;
    /**
     * 参数对象
     */
    private Map<String, Object> paramMap;

    public static RacContext of() {
        return new RacContext();
    }

    public UserRdo getUserRdo() {
        return userRdo;
    }

    public RacContext userRdo(UserRdo userRdo) {
        this.userRdo = userRdo;
        return this;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public RacContext paramMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
        return this;
    }
}
