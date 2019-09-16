package com.zwan.generator.core.injector.methods;


import com.zwan.generator.core.enums.SqlMethod;
import com.zwan.generator.core.injector.AbstractMethod;
import com.zwan.generator.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 查询满足条件一条数据
 *
 * @author hubin
 * @since 2018-04-06
 */
public class SelectOne extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod sqlMethod = SqlMethod.SELECT_ONE;
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, String.format(sqlMethod.getSql(),
            this.sqlSelectColumns(tableInfo, true), tableInfo.getTableName(),
            this.sqlWhereEntityWrapper(true, tableInfo), sqlComment()),
            modelClass);
        return this.addSelectMappedStatementForTable(mapperClass, sqlMethod.getMethod(), sqlSource, tableInfo);
    }
}
