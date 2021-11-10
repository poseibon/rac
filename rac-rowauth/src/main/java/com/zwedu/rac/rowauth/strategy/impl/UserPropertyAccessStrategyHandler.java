package com.zwedu.rac.rowauth.strategy.impl;

import com.zwedu.rac.domain.common.AuthInfo;
import com.zwedu.rac.rowauth.enums.StrategyTypeEnum;
import com.zwedu.rac.rowauth.strategy.DataAccessStrategyHandler;
import com.zwedu.rac.rowauth.strategy.DataAccessStrategyHandlerBuilder;
import com.zwedu.rac.rowauth.strategy.entity.UserPropertyStrategyInfo;
import com.zwedu.rac.sdk.vo.RacContext;
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
