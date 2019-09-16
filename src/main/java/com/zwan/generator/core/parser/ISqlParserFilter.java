package com.zwan.generator.core.parser;

import org.apache.ibatis.reflection.MetaObject;

/**
 * SQL 解析过滤器
 *
 * @author hubin
 * @since 2017-09-02
 */
public interface ISqlParserFilter {

    boolean doFilter(MetaObject metaObject);

}
