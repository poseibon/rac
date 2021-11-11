package org.poseibon.rac.rowauth.strategy.impl;

import org.poseibon.common.auth.AuthInfo;
import org.poseibon.rac.rowauth.enums.StrategyTypeEnum;
import org.poseibon.rac.rowauth.strategy.DataAccessStrategyHandler;
import org.poseibon.rac.rowauth.strategy.DataAccessStrategyHandlerBuilder;
import org.poseibon.rac.rowauth.strategy.entity.UserPropertyStrategyInfo;
import org.poseibon.rac.sdk.vo.RacContext;
import org.poseibon.common.validator.ParamAssert;

/**
 * 访问控制策略<p>用户属性</p>表达式处理器
 *
 * @author qingchuan
 * @date 2020/12/24
 */
public class UserPropertyAccessStrategyHandler implements DataAccessStrategyHandler<UserPropertyStrategyInfo> {

    @Override
    public boolean hasAuth(UserPropertyStrategyInfo strategyInfo, RacContext racContext) {
        return DataAccessStrategyHandlerBuilder.instance(StrategyTypeEnum.USER_PROPERTY.getText(),
                strategyInfo.getPropertyType()).hasAuth(strategyInfo, racContext);
    }

    @Override
    public AuthInfo getAuthInfo(UserPropertyStrategyInfo strategyInfo, RacContext racContext) {
        ParamAssert.PARAM_EMPTY_ERROR.allNotNull(strategyInfo, racContext.getUserRdo());
        return DataAccessStrategyHandlerBuilder.instance(StrategyTypeEnum.USER_PROPERTY.getText(),
                strategyInfo.getPropertyType()).getAuthInfo(strategyInfo, racContext);
    }
}
