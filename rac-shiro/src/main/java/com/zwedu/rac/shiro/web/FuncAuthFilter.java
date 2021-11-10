package com.zwedu.rac.shiro.web;

import com.zwedu.rac.sdk.threadlocal.RacContextThreadLocal;
import com.zwedu.rac.sdk.vo.RacContext;
import com.zwedu.rac.sdk.vo.UserSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.poseibon.common.enums.ResponseCodeEnum;
import org.poseibon.common.utils.Strings2;
import org.poseibon.common.utils.UrlMatcher;
import org.poseibon.common.web.AbstractHttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.TreeSet;

/**
 * 功能权限过滤器
 *
 * @author qingchuan
 * @date 2021/1/10
 */
public class FuncAuthFilter implements Filter, AbstractHttpServletRequest {

    public static final Logger log = LoggerFactory.getLogger(FuncAuthFilter.class);

    private String loginUrl;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 看看这个路径权限里有没有维护，如果没有维护，一律放行(也可以改为一律不放行)
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            log.error("not login or session expired!");
            // 没有登录的话，跳转到登录页
            if (isAjaxRequest((HttpServletRequest) request)) {
                String template = "{\"status\":%s,\"msg\":\"%s\"}";
                printMessage((HttpServletResponse) response, template,
                        ResponseCodeEnum.NO_AUTH.getValue(), ResponseCodeEnum.NO_AUTH.getText());
            } else {
                // 跳转到登陆页
                printMessage((HttpServletResponse) response, TEMPLATE, loginUrl);
            }
            return;
        }
        Session session = SecurityUtils.getSubject().getSession();
        UserSession userSession = (UserSession) session.getAttribute("userSession");
        String contextPath = ((HttpServletRequest) request).getContextPath();
        String requestURI = ((HttpServletRequest) request).getServletPath();
        String path = contextPath + requestURI;
        TreeSet<String> urlSet = new TreeSet<>(userSession.getFuncStrategyMap().keySet());
        String url = UrlMatcher.getInstance().find(urlSet, path);
        if (Strings2.isEmpty(url)) {
            log.error("url:{} not permitted!", requestURI);
            // 没有登录的话，跳转到登录页
            if (isAjaxRequest((HttpServletRequest) request)) {
                String template = "{\"status\":%s,\"msg\":\"%s\"}";
                printMessage((HttpServletResponse) response, template,
                        ResponseCodeEnum.NO_AUTH.getValue(), ResponseCodeEnum.NO_AUTH.getText());
            } else {
                // 跳转到登陆页
                printMessage((HttpServletResponse) response, TEMPLATE, loginUrl);
            }
            return;
        }

        try {
            RacContextThreadLocal.CURRENT_RAC_CONTEXT.set(RacContext.of()
                    .strategyRdo(userSession.getFuncStrategyMap().get(url))
                    .userRdo(userSession.getUserRdo()));
            chain.doFilter(request, response);
        } finally {
            RacContextThreadLocal.CURRENT_RAC_CONTEXT.remove();
        }
    }

    public FuncAuthFilter(String loginUrl) {
        this.loginUrl = loginUrl;
    }

}