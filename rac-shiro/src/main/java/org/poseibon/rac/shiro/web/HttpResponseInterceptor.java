package org.poseibon.rac.shiro.web;

import com.google.common.collect.Lists;
import org.poseibon.rac.shiro.utils.Jackson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.poseibon.common.exception.BaseException;
import org.poseibon.common.utils.BaseResponse;
import org.poseibon.common.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 自定义拦截器-方法拦截器，基于spring aop
 *
 * @author qingchuan
 * @date 2020/12/9
 */
@Aspect
public class HttpResponseInterceptor {
    public static final Logger log = LoggerFactory.getLogger(HttpResponseInterceptor.class);

    @Around("@annotation(org.springframework.web.bind.annotation.ResponseBody)")
    public Object handleResponse(ProceedingJoinPoint pjp) {
        LogRecord buildLog = buildLog(pjp);
        try {
            Object result = pjp.proceed();
            log.info("invoke {}.{}, param:{}",
                    buildLog.clazz, buildLog.method, Jackson.toJson(buildLog.getParamList()));
            return result;
        } catch (BaseException ex) {
            log.error("invoke {}.{}, param:{}, ex-code:{}, ex-msg={}, {}",
                    buildLog.clazz, buildLog.method, Jackson.toJson(buildLog.getParamList()),
                    ex.getCode(), ex.getMsg(), ex);
            BaseResponse responseData = ResponseUtil.fail(ex.getCode(), ex.getMsg());
            return responseData;
        } catch (Throwable ex) {
            log.error("invoke {}.{}, param:{}, ex-msg={}, {}",
                    buildLog.clazz, buildLog.method, Jackson.toJson(buildLog.getParamList()),
                    ex.getMessage(), ex);
            BaseResponse responseData = ResponseUtil.fail("系统异常，请稍后再试");
            return responseData;
        }
    }

    /**
     * 构建日志对象
     *
     * @param pjp 拦截点
     * @return 日志对象
     */
    private LogRecord buildLog(ProceedingJoinPoint pjp) {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Object[] paramValues = pjp.getArgs();
        Class<?> clazz = pjp.getTarget().getClass();
        String[] paramNames = methodSignature.getParameterNames();
        List<String> kvList = Lists.newArrayList();
        if (paramNames != null) {
            for (int i = 0; i < paramNames.length; i++) {
                String paramName = paramNames[i];
                Object paramValue = paramValues[i];
                kvList.add(paramName + ":" + Jackson.toJson(paramValue));
            }
        }
        return LogRecord.builder(clazz.getName(), methodSignature.getName())
                .paramList(kvList);
    }

    public static class LogRecord {
        /**
         * 类名
         */
        private String clazz;
        /**
         * 方法名
         */
        private String method;
        /**
         * 参数
         */
        private List<String> paramList;

        public static LogRecord builder(String clazz, String method){
            LogRecord record = new LogRecord();
            record.setClazz(clazz);
            record.setMethod(method);
            return record;
        }

        public String getClazz() {
            return clazz;
        }

        public void setClazz(String clazz) {
            this.clazz = clazz;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public List<String> getParamList() {
            return paramList;
        }

        public LogRecord paramList(List<String> paramList) {
            this.paramList = paramList;
            return this;
        }
    }
}