package com.zwedu.rac.sdk.threadlocal;

import com.zwedu.rac.sdk.vo.RacContext;

/**
 * 当前策略threadlocal
 *
 * @author qingchuan
 * @date 2020/12/25
 */
public interface RacContextThreadLocal {
    /**
     * 当前策略
     */
    ThreadLocal<RacContext> CURRENT_RAC_CONTEXT = new ThreadLocal<>();
}
