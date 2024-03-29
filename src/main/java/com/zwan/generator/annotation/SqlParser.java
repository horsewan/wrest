package com.zwan.generator.annotation;

import java.lang.annotation.*;

/**
 * 租户注解
 *
 * @author hubin
 * @since 2018-01-13
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface SqlParser {

    /**
     * 过滤 SQL 解析，默认 false
     */
    boolean filter() default false;
}
