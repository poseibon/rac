package org.poseibon.rac.rowauth.strategy.impl;

import org.poseibon.common.enums.DataAccessEnum;
import org.poseibon.rac.rowauth.strategy.DataAccessStrategyHandler;
import org.poseibon.rac.rowauth.strategy.vo.AllStrategyInfo;
import org.poseibon.rac.rowauth.strategy.vo.AuthInfo;
import org.poseibon.rac.sdk.vo.RacContext;

/**
 * 访问控制策略<p>全部</p>表达式处理器
 *
 * @author qingchuan
 * @date 2020/12/24
 */
public class AllAccessStrategyHandler implements DataAccessStrategyHandler<AllStrategyInfo> {

    @Override
    public boolean hasAuth(AllStrategyInfo strategyRdo, RacContext racContext) {
        return true;
    }

    @Override
    public AuthInfo getAuthInfo(AllStrategyInfo strategyRdo, RacContext racContext) {
        return AuthInfo.of().dataAccess(DataAccessEnum.ALL);
    }
}
