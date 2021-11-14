package org.poseibon.rac.rowauth.strategy;

import org.poseibon.rac.rowauth.strategy.vo.AuthInfo;
import org.poseibon.rac.rowauth.strategy.vo.StrategyInfo;
import org.poseibon.rac.sdk.vo.RacContext;

/**
 * 策略表达式处理器
 *
 * @author qingchuan
 * @date 2020/12/24
 */
public interface DataAccessStrategyHandler<T extends StrategyInfo> {
    /**
     * 是否有权限操作
     *
     * @param strategyInfo 策略对象
     * @param racContext   上下文
     * @return true or false
     */
    boolean hasAuth(T strategyInfo, RacContext racContext);


    /**
     * 是否有权限操作
     *
     * @param strategyInfo 策略对象
     * @param racContext   上下文
     * @return true or false
     */
    boolean hasAuth(AuthInfo authInfo, T strategyInfo, RacContext racContext);

    /**
     * 策略表达式处理方法
     *
     * @param strategyRdo 策略对象
     * @param racContext  上下文
     * @return 权限信息
     */
    AuthInfo getAuthInfo(T strategyRdo, RacContext racContext);
}
