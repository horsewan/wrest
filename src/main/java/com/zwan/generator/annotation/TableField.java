package com.zwan.generator.annotation;

import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.UnknownTypeHandler;

import java.lang.annotation.*;


/**
 * 表字段标识
 *
 * @author hubin sjy tantan
 * @since 2016-09-09
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TableField {

    /**
     * 字段值（驼峰命名方式,该值可无）
     */
    String value() default "";

    /**
     * 是否为数据库表字段
     * 默认 true 存在，false 不存在
     */
    boolean exist() default true;

    /**
     * 字段 where 实体查询比较条件
     * 默认 `=` 等值
     */
    String condition() default "";

    /**
     * 字段 update set 部分注入, 该注解优于 el 注解使用
     * <p>
     * 例1：@TableField(.. , update="%s+1") 其中 %s 会填充为字段
     * 输出 SQL 为：update 表 set 字段=字段+1 where ...
     * <p>
     * 例2：@TableField(.. , update="now()") 使用数据库时间
     * 输出 SQL 为：update 表 set 字段=now() where ...
     */
    String update() default "";

    /**
     * 字段验证策略之 insert: 当insert操作时，该字段拼接insert语句时的策略
     * IGNORED: 直接拼接 insert into table_a(column) values (#{columnProperty});
     * NOT_NULL: insert into table_a(<if test="columnProperty != null">column</if>) values (<if test="columnProperty != null">#{columnProperty}</if>)
     * NOT_EMPTY: insert into table_a(<if test="columnProperty != null and columnProperty!=''">column</if>) values (<if test="columnProperty != null and columnProperty!=''">#{columnProperty}</if>)
     *
     * @since 3.1.2
     */
    FieldStrategy insertStrategy() default FieldStrategy.DEFAULT;

    /**
     * 字段验证策略之 update: 当更新操作时，该字段拼接set语句时的策略
     * IGNORED: 直接拼接 update table_a set column=#{columnProperty}, 属性为null/空string都会被set进去
     * NOT_NULL: update table_a set <if test="columnProperty != null">column=#{columnProperty}</if>
     * NOT_EMPTY: update table_a set <if test="columnProperty != null and columnProperty!=''">column=#{columnProperty}</if>
     *
     * @since 3.1.2
     */
    FieldStrategy updateStrategy() default FieldStrategy.DEFAULT;

    /**
     * 字段验证策略之 where: 表示该字段在拼接where条件时的策略
     * IGNORED: 直接拼接 column=#{columnProperty}
     * NOT_NULL: <if test="columnProperty != null">column=#{columnProperty}</if>
     * NOT_EMPTY: <if test="columnProperty != null and columnProperty!=''">column=#{columnProperty}</if>
     *
     * @since 3.1.2
     */
    FieldStrategy whereStrategy() default FieldStrategy.DEFAULT;

    /**
     * 字段自动填充策略
     */
    FieldFill fill() default FieldFill.DEFAULT;

    /**
     * 是否进行 select 查询
     * <p>大字段可设置为 false 不加入 select 查询范围</p>
     */
    boolean select() default true;

    /**
     * 是否保持使用全局的 Format 的值
     * <p> 只生效于 既设置了全局的 Format 也设置了上面 {@link #value()} 的值 </p>
     * <li> 如果是 false , 全局的 Format 不生效 </li>
     *
     * @since 3.1.1
     */
    boolean keepGlobalFormat() default false;

    /**
     * JDBC类型 (该默认值不代表会按照该值生效)
     * <p>
     * {@link ResultMapping#jdbcType} and {@link ParameterMapping#jdbcType}
     *
     * @since 3.1.2
     */
    JdbcType jdbcType() default JdbcType.UNDEFINED;

    /**
     * 类型处理器 (该默认值不代表会按照该值生效)
     * <p>
     * {@link ResultMapping#typeHandler} and {@link ParameterMapping#typeHandler}
     *
     * @since 3.1.2
     */
    Class<? extends TypeHandler> typeHandler() default UnknownTypeHandler.class;

    /**
     * 指定小数点后保留的位数
     * <p>
     * {@link ParameterMapping#numericScale}
     *
     * @since 3.1.2
     */
    String numericScale() default "";
}
