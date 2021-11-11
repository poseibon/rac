package com.zwedu.rac.rowauth.strategy.impl;

import com.zwedu.rac.rowauth.enums.DataAccessFieldEnum;
import com.zwedu.rac.rowauth.strategy.DataAccessStrategyHandler;
import com.zwedu.rac.rowauth.strategy.entity.UserPropertyStrategyInfo;
import com.zwedu.rac.sdk.rdo.user.UserRdo;
import com.zwedu.rac.sdk.vo.RacContext;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.poseibon.common.auth.AuthInfo;
import org.poseibon.common.constant.SeparationChar;
import org.poseibon.common.enums.DataAccessEnum;
import org.poseibon.common.utils.Strings2;
import org.poseibon.common.validator.ParamAssert;

/**
 * 访问控制策略<p>用户属性</p>表达式处理器
 *
 * @author qingchuan
 * @date 2020/12/24
 */
public class UserExtPropertyAccessStrategyHandler
        implements DataAccessStrategyHandler<UserPropertyStrategyInfo> {
    @Override
    public boolean hasAuth(UserPropertyStrategyInfo strategyInfo, RacContext racContext) {
        AuthInfo authInfo = getAuthInfo(strategyInfo, racContext);
        if (CollectionUtils.isNotEmpty(authInfo.getAuthList())
                && MapUtils.isNotEmpty(racContext.getParamMap())
                && racContext.getParamMap().containsKey(strategyInfo.getEntityPropertyName())
                && authInfo.getAuthList().contains(ObjectUtils.toString(racContext.getParamMap()
                .get(strategyInfo.getEntityPropertyName()), () -> Strings2.EMPTY))) {
            return true;
        }
        return false;
    }

    @Override
    public AuthInfo getAuthInfo(UserPropertyStrategyInfo strategyInfo, RacContext racContext) {
        ParamAssert.PARAM_EMPTY_ERROR.allNotNull(strategyInfo, racContext.getUserRdo());
        UserRdo userRdo = racContext.getUserRdo();
        // 扩展属性的话，从扩展属性数据中获取
        if (MapUtils.isNotEmpty(userRdo.getExtData())
                && userRdo.getExtData().containsKey(strategyInfo.getPropertyName())) {
            String value = ObjectUtils.toString(userRdo.getExtData()
                    .get(strategyInfo.getPropertyName()), () -> Strings2.EMPTY);
            if (Strings2.isEmpty(value)) {
                return AuthInfo.of().dataAccess(DataAccessEnum.NONE);
            }
            return AuthInfo.of().dataAccess(DataAccessEnum.DECENTRALIZED)
                    .fieldName(strategyInfo.getEntityPropertyName())
                    .dbFieldName(DataAccessFieldEnum.of(strategyInfo.getEntityPropertyName()).getText())
                    .authList(Strings2.toStringList(value, SeparationChar.COMMA));
        }
        return AuthInfo.of().dataAccess(DataAccessEnum.NONE);
    }
}
