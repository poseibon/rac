package org.poseibon.rac.rowauth.annotation;

import java.lang.annotation.*;

/**
 * 数据权限实现的注解
 *
 * @author qingchuan
 * @date 2020/12/21
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RowFilter {
    /**
     * sql中需要过滤行权限的表
     */
    String tblName() default "";
}