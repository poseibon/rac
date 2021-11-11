package org.poseibon.rac.shiro.web;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;

/**
 * 用户session 操作接口
 * SessionDAO的作用是为Session提供CRUD并进行持久化的一个shiro组件
 * MemorySessionDAO 直接在内存中进行会话维护
 * EnterpriseCacheSessionDAO  提供了缓存功能的会话维护，默认情况下使用MapCache实现，内部使用ConcurrentHashMap保存缓存的会话。
 *
 * @author qingchuan
 * @date 2020/12/12
 */
public class UserSessionDao extends EnterpriseCacheSessionDAO {
    /**
     * 构造函数
     *
     * @param bizLineId    业务线ID
     * @param cacheManager 缓存管理器
     */
    public UserSessionDao(Long bizLineId) {
        //sessionId生成器
        this.setSessionIdGenerator(new JavaUuidSessionIdGenerator());
//        this.setCacheManager(cacheManager);
        this.setActiveSessionsCacheName("Shiro-ActiveSessionsCache-" + bizLineId);
    }
}
