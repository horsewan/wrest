
package com.zwan.generator.core;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;

/**
 * 继承 {@link XMLLanguageDriver} 重装构造函数, 使用自己的 MybatisDefaultParameterHandler
 *
 * @author hubin
 * @since 2016-03-11
 */
public class MybatisXMLLanguageDriver extends XMLLanguageDriver {

    @Override
    public MybatisDefaultParameterHandler createParameterHandler(MappedStatement mappedStatement,
                                                                 Object parameterObject, BoundSql boundSql) {
        // TODO 使用 MybatisDefaultParameterHandler 而不是 ParameterHandler
        return new MybatisDefaultParameterHandler(mappedStatement, parameterObject, boundSql);
    }
}
