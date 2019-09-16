package com.zwan.generator.core.incrementer;


/**
 * 表主键生成器接口 (sql)
 *
 * @author hubin
 * @since 2017-05-08
 */
public interface IKeyGenerator {

    /**
     * 执行 key 生成 SQL
     *
     * @param incrementerName 序列名称
     * @return sql
     */
    String executeSql(String incrementerName);
}
