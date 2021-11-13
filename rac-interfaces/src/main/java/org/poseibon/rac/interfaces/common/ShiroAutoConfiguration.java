package org.poseibon.rac.interfaces.common;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.poseibon.rac.sdk.provider.AuthProvider;
import org.poseibon.rac.shiro.service.UserSessionProvider;
import org.poseibon.rac.shiro.web.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置
 *
 * @author qingchuan
 * @date 2020/12/12
 */
@Configuration
public class ShiroAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(ShiroAutoConfiguration.class);

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private AuthProvider authProvider;

    @Value("${server.url.login}")
    private String loginUrl;
    @Value("${server.url.success}")
    private String successUrl;
    @Value("${rac.bizLineId}")
    private Long bizLineId;
    @Value("${rac.cookieName}")
    private String cookieName;
    @Value("${rac.sessionValidationInterval}")
    private Long sessionValidationInterval;

    @Bean("userSessionProvider")
    public UserSessionProvider userSessionProvider() {
        return new UserSessionProvider(authProvider);
    }

    @Bean("httpResponseInterceptor")
    public HttpResponseInterceptor httpResponseInterceptor() {
        return new HttpResponseInterceptor();
    }

    @Bean(name = "securityManager")
    public SecurityManager securityManager() {
        return new RacSecurityManager(new RacAuthorizingRealm(authProvider, bizLineId),
                new RacWebSessionManager(bizLineId, cookieName, ImmutableList.of(new RacSessionListener()),
                        sessionValidationInterval),
                new RedisShiroCacheManager(redisTemplate));
    }


    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        log.info("init shiro filter...");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        // 登出匹配
        map.put("/logout", "anon");
        map.put("/login", "anon");
        // 其他请求，功能授权拦截
        map.put("/**", "funcAuth");
        // 登录页面
        shiroFilterFactoryBean.setLoginUrl(loginUrl);
        // 首页
        shiroFilterFactoryBean.setSuccessUrl(successUrl);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        Map<String, Filter> filterMap = Maps.newHashMap();
        filterMap.put("anon", new AnonymousFilter());
        filterMap.put("funcAuth", new FuncAuthFilter(loginUrl));
        shiroFilterFactoryBean.setFilters(filterMap);
        return shiroFilterFactoryBean;
    }
}
