package org.poseibon.rac.rowauth.strategy.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.poseibon.common.enums.DataAccessEnum;
import org.poseibon.common.validator.ParamAssert;
import org.poseibon.rac.rowauth.strategy.DataAccessStrategyHandler;
import org.poseibon.rac.rowauth.strategy.vo.AuthInfo;
import org.poseibon.rac.rowauth.strategy.vo.FixStrategyInfo;
import org.poseibon.rac.sdk.vo.RacContext;

/**
 * 访问控制策略<p>固定值</p>表达式处理器
 *
 * @author qingchuan
 * @date 2020/12/24
 */
public class FixAccessStrategyHandler implements DataAccessStrategyHandler<FixStrategyInfo> {


    @Override
    public boolean hasAuth(FixStrategyInfo strategyInfo, RacContext racContext) {
        return false;
    }

    @Override
    public boolean hasAuth(AuthInfo authInfo, FixStrategyInfo strategyInfo, RacContext racContext) {
        return false;
    }

    @Override
    public AuthInfo getAuthInfo(FixStrategyInfo strategyRdo, RacContext racContext) {
        ParamAssert.PARAM_EMPTY_ERROR.allNotNull(strategyRdo);
        // 扩展属性的话，从扩展属性数据中获取
        if (CollectionUtils.isNotEmpty(strategyRdo.getAuthList())) {
            return AuthInfo.of().dataAccess(DataAccessEnum.DECENTRALIZED)
                    .authList(strategyRdo.getAuthList());
        }
        return AuthInfo.of().dataAccess(DataAccessEnum.NONE);
    }
}
