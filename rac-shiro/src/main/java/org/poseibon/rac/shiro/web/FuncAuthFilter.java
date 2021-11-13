package org.poseibon.rac.shiro.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.poseibon.common.utils.Strings2;
import org.poseibon.common.utils.UrlMatcher;
import org.poseibon.rac.sdk.threadlocal.RacContextThreadLocal;
import org.poseibon.rac.sdk.vo.RacContext;
import org.poseibon.rac.sdk.vo.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.TreeSet;

import static org.poseibon.rac.shiro.utils.HttpHelper.handleRequest;

/**
 * 功能权限过滤器
 *
 * @author qingchuan
 * @date 2021/1/10
 */
public class FuncAuthFilter implements Filter {

    public static final Logger log = LoggerFactory.getLogger(FuncAuthFilter.class);

    private String loginUrl;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 拿到登陆用户信息，校验是否登陆
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            log.error("not login or session expired!");
            // 没有登录的话，跳转到登录页
            handleRequest((HttpServletRequest) request, (HttpServletResponse) response, loginUrl);
            return;
        }
        // 获取session信息
        Session session = SecurityUtils.getSubject().getSession();
        UserSession userSession = (UserSession) session.getAttribute("userSession");
        String contextPath = ((HttpServletRequest) request).getContextPath();
        String requestURI = ((HttpServletRequest) request).getServletPath();
        String path = contextPath + requestURI;
        TreeSet<String> urlSet = new TreeSet<>(userSession.getFuncStrategyMap().keySet());
        String url = UrlMatcher.getInstance().find(urlSet, path);
        // 检查访问url是否有授权
        if (Strings2.isEmpty(url)) {
            log.error("url:{} not permitted!", requestURI);
            // 没有登录的话，跳转到登录页
            handleRequest((HttpServletRequest) request, (HttpServletResponse) response, loginUrl);
            return;
        }
        // 授权校验通过后，拿到当前url对应的数据访问策略
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