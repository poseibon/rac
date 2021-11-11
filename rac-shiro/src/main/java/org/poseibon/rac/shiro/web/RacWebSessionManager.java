package org.poseibon.rac.shiro.web;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import java.util.ArrayList;
import java.util.Collection;

/**
 * web session 管理器
 *
 * @author qingchuan
 * @date 2020/12/9
 */
public class RacWebSessionManager extends DefaultWebSessionManager {
    /**
     * web session 管理器
     *
     * @param bizLineId                 业务线ID
     * @param cookieName                cookie名称
     * @param sessionListeners          session监听器列表
     * @param sessionValidationInterval session校验时间间隔
     */
    public RacWebSessionManager(Long bizLineId, String cookieName, Collection<SessionListener> sessionListeners,
                                Long sessionValidationInterval) {
        this.setSessionListeners(sessionListeners);
        this.setSessionIdCookie(new RacCookie(cookieName));
        this.setSessionIdCookieEnabled(true);
        this.setSessionDAO(new UserSessionDao(bizLineId));
        //全局会话超时时间（单位毫秒），默认30分钟  暂时设置为10秒钟 用来测试
//        sessionManager.setGlobalSessionTimeout(10000);
        //是否开启删除无效的session对象  默认为true
        this.setDeleteInvalidSessions(true);
        //是否开启定时调度器进行检测过期session 默认为true
        this.setSessionValidationSchedulerEnabled(true);
        //设置session失效的扫描时间, 清理用户直接关闭浏览器造成的孤立会话 默认为 1个小时
        //设置该属性 就不需要设置 ExecutorServiceSessionValidationScheduler
        // 底层也是默认自动调用ExecutorServiceSessionValidationScheduler
        //暂时设置为 5秒 用来测试
        this.setSessionValidationInterval(sessionValidationInterval);
        //取消url 后面的 JSESSIONID
        this.setSessionIdUrlRewritingEnabled(false);
    }
}
