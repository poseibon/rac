package org.poseibon.rac.shiro.utils;


import org.poseibon.common.enums.ResponseCodeEnum;
import org.poseibon.common.utils.Strings2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 抽象http处理
 *
 * @author qingchuan
 * @date 2021/1/10
 */
public class HttpHelper {
    /**
     * ajax 请求头
     */
    public static final String AJAX_HEADER = "X-Requested-With";
    /**
     * ajax 请求头值
     */
    public static final String AJAX_HEADER_CONTENT = "XMLHttpRequest";
    /**
     * 跳转模板
     */
    public static final String TEMPLATE = "<script type='text/javascript'>(window.parent||window).location.replace('%s');</script>";

    /**
     * 判断是否是ajax请求
     *
     * @param request HttpServletRequest对象
     * @return true 代表是 FALSE 代表否
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestType = request.getHeader(AJAX_HEADER) == null ? Strings2.EMPTY :
                request.getHeader(AJAX_HEADER).trim();
        if (Strings2.isNotEmpty(requestType) && requestType.equals(AJAX_HEADER_CONTENT)) {
            return true;
        }
        return false;
    }

    /**
     * 处理请求
     *
     * @param request  请求
     * @param response 相应
     */
    public static void handleRequest(HttpServletRequest request, HttpServletResponse response, String loginUrl)
            throws IOException {
        // 没有登录的话，跳转到登录页
        if (isAjaxRequest(request)) {
            String template = "{\"status\":%s,\"msg\":\"%s\"}";
            writeMessage(response, template,
                    ResponseCodeEnum.NO_AUTH.getValue(), ResponseCodeEnum.NO_AUTH.getText());
        } else {
            // 跳转到登陆页
            redirect(response, loginUrl);
        }
    }

    /**
     * 输出错误信息
     *
     * @param httpResponse 响应句柄
     * @param args         参数
     * @throws IOException 异常
     */
    public static void redirect(HttpServletResponse httpResponse, Object... args) throws IOException {
        writeMessage(httpResponse, TEMPLATE, args);
    }

    /**
     * 输出错误信息
     *
     * @param httpResponse 响应句柄
     * @param template     模板
     * @param args         参数
     * @throws IOException 异常
     */
    public static void writeMessage(HttpServletResponse httpResponse, String template, Object... args) throws IOException {
        PrintWriter writer = httpResponse.getWriter();
        writer.write(String.format(template, args));
        writer.flush();
        writer.close();
    }
}
