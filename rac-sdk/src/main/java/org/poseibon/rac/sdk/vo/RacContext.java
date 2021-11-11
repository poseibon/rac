package org.poseibon.rac.sdk.vo;

import org.poseibon.rac.sdk.rdo.strategy.StrategyRdo;
import org.poseibon.rac.sdk.rdo.user.UserRdo;

import java.util.Map;

/**
 * 用户上线文信息
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
     * 策略数据
     */
    private StrategyRdo strategyRdo;
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

    public StrategyRdo getStrategyRdo() {
        return strategyRdo;
    }

    public RacContext strategyRdo(StrategyRdo strategyRdo) {
        this.strategyRdo = strategyRdo;
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
