package com.zwan.generator.annotation;

import java.lang.annotation.*;

/**
 * 乐观锁注解、标记 @Verison 在字段上
 *
 * @author TaoYu
 * @since 2016-01-23
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Version {}
