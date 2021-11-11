package org.poseibon.rac.shiro.web;

import org.apache.shiro.web.servlet.SimpleCookie;

/**
 * cookie配置
 * 配置保存sessionId的cookie
 * 注意：这里的cookie 不是上面的记住我 cookie 记住我需要一个cookie session管理 也需要自己的cookie
 *
 * @author qingchuan
 * @date 2020/12/12
 */
public class RacCookie extends SimpleCookie {
    /**
     * 构造器
     *
     * @param cookieName cookie名称
     */
    public RacCookie(String cookieName) {
        this.setName(cookieName);
        //防止xss读取cookie
        this.setHttpOnly(true);
        this.setPath("/");
        //maxAge=-1表示浏览器关闭时失效此Cookie
        this.setMaxAge(-1);
    }
}
