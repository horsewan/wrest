package com.zwan.generator.annotation;

import java.lang.annotation.*;

/**
 * 序列主键策略
 * <p>oracle</p>
 *
 * @author zashitou
 * @since 2017.4.20
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface KeySequence {

    /**
     * 序列名
     */
    String value() default "";

    /**
     * id的类型
     */
    Class<?> clazz() default Long.class;
}
