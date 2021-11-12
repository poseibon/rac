package org.poseibon.rac.shiro.utils;


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
     * 输出错误信息
     *
     * @param httpResponse 响应句柄
     * @param args         参数
     * @throws IOException 异常
     */
    public static void printMessage(HttpServletResponse httpResponse, Object... args) throws IOException {
        printMessage(httpResponse, TEMPLATE, args);
    }

    /**
     * 输出错误信息
     *
     * @param httpResponse 响应句柄
     * @param template     模板
     * @param args         参数
     * @throws IOException 异常
     */
    public static void printMessage(HttpServletResponse httpResponse, String template, Object... args) throws IOException {
        PrintWriter writer = httpResponse.getWriter();
        writer.write(String.format(template, args));
        writer.flush();
        writer.close();
    }
}
