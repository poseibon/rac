package com.zwedu.rac.rowauth.strategy.impl;

import com.zwedu.rac.domain.common.AuthInfo;
import com.zwedu.rac.rowauth.strategy.DataAccessStrategyHandler;
import com.zwedu.rac.rowauth.strategy.entity.DimensionStrategyInfo;
import com.zwedu.rac.sdk.vo.RacContext;

/**
 * 访问控制策略<p>用户维度</p>表达式处理器
 *
 * @author qingchuan
 * @date 2020/12/24
 */
public class DimensionAccessStrategyHandler implements DataAccessStrategyHandler<DimensionStrategyInfo> {

    @Override
    public boolean hasAuth(DimensionStrategyInfo strategyRdo, RacContext racContext) {
        return false;
    }

    @Override
    public AuthInfo getAuthInfo(DimensionStrategyInfo strategyRdo, RacContext racContext) {
        return null;
    }
}
