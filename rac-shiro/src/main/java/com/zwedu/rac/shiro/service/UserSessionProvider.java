package com.zwedu.rac.shiro.service;

import com.google.common.collect.Maps;
import com.zwedu.rac.sdk.provider.AuthProvider;
import com.zwedu.rac.sdk.rdo.FuncRdo;
import com.zwedu.rac.sdk.rdo.StrategyRdo;
import com.zwedu.rac.sdk.rdo.UserRdo;
import com.zwedu.rac.sdk.rpo.auth.FuncAuthRpo;
import com.zwedu.rac.sdk.vo.UserSession;
import org.poseibon.common.validator.ParamAssert;

import java.util.List;
import java.util.Map;

/**
 * 用户session管理
 *
 * @author qingchuan
 * @date 2020/12/25
 */
public class UserSessionProvider {
    /**
     * 授权服务
     */
    private AuthProvider authProvider;

    /**
     * 根据UserId获取session信息
     *
     * @param bizLineId 业务线ID
     * @param userName  用户名
     * @return session信息
     */
    public UserSession getSession(Long bizLineId, String userName) {
        ParamAssert.PARAM_EMPTY_ERROR.notNull(userName);
        // 获取用户信息
        UserRdo userRdo = authProvider.queryUser(bizLineId, userName);
        // 获取功能权限策略
        List<FuncRdo> funcRdoList = authProvider.listFuncAuth(new FuncAuthRpo(bizLineId, userRdo.getUserId()));
        Map<String, StrategyRdo> funcStrategyMap = Maps.newHashMap();
        for (FuncRdo funcRdo : funcRdoList) {
            for (String url : funcRdo.getUrlSet()) {
                funcStrategyMap.put(url, funcRdo.getStrategyRdo());
            }
        }
        return UserSession.builder().userRdo(userRdo).funcStrategyMap(funcStrategyMap);
    }

    /**
     * 构造函数
     *
     * @param authProvider 权限查询接口
     */
    public UserSessionProvider(AuthProvider authProvider) {
        this.authProvider = authProvider;
    }
}
