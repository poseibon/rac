package com.zwedu.rac.sdk.rdo.role;

import com.zwedu.rac.sdk.rdo.strategy.StrategyRdo;

import java.io.Serializable;
import java.util.Collection;

/**
 * 功能rdo
 *
 * @author qingchuan
 * @date 2020/12/21
 */
public class FuncRdo implements Serializable {
    /**
     * url列表
     */
    private Collection<String> urlSet;
    /**
     * 对应访问策略
     */
    private StrategyRdo strategyRdo;

    public Collection<String> getUrlSet() {
        return urlSet;
    }

    public void setUrlSet(Collection<String> urlSet) {
        this.urlSet = urlSet;
    }

    public StrategyRdo getStrategyRdo() {
        return strategyRdo;
    }

    public void setStrategyRdo(StrategyRdo strategyRdo) {
        this.strategyRdo = strategyRdo;
    }
}
