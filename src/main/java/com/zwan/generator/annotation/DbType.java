package com.zwan.generator.annotation;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * MybatisPlus 数据库类型
 *
 * @since 2018-06-23
 */
@Getter
@AllArgsConstructor
public enum DbType {
    /**
     * MYSQL
     */
    MYSQL("mysql"),   //, "MySql数据库", "com.baomidou.mybatisplus.extension.plugins.pagination.dialects.MySqlDialect"
    /**
     * MARIADB
     */
    MARIADB("mariadb"),  //, "MariaDB数据库", "com.baomidou.mybatisplus.extension.plugins.pagination.dialects.MariaDBDialect"
    /**
     * ORACLE
     */
    ORACLE("oracle"),  //, "Oracle数据库", "com.baomidou.mybatisplus.extension.plugins.pagination.dialects.OracleDialect"
    /**
     * DB2
     */
    DB2("db2"),  //, "DB2数据库", "com.baomidou.mybatisplus.extension.plugins.pagination.dialects.DB2Dialect"
    /**
     * H2
     */
    H2("h2"),  //, "H2数据库", "com.baomidou.mybatisplus.extension.plugins.pagination.dialects.H2Dialect"
    /**
     * HSQL
     */
    HSQL("hsql"),  //, "HSQL数据库", "com.baomidou.mybatisplus.extension.plugins.pagination.dialects.HSQLDialect"
    /**
     * SQLITE
     */
    SQLITE("sqlite"), //, "SQLite数据库", "com.baomidou.mybatisplus.extension.plugins.pagination.dialects.SQLiteDialect"
    /**
     * POSTGRE
     */
    POSTGRE_SQL("postgresql"), //, "Postgre数据库", "com.baomidou.mybatisplus.extension.plugins.pagination.dialects.PostgreDialect"
    /**
     * SQLSERVER2005
     */
    SQL_SERVER2005("sqlserver2005"), //, "SQLServer2005数据库", "com.baomidou.mybatisplus.extension.plugins.pagination.dialects.SQLServer2005Dialect"
    /**
     * SQLSERVER
     */
    SQL_SERVER("sqlserver"), //, "SQLServer数据库", "com.baomidou.mybatisplus.extension.plugins.pagination.dialects.SQLServerDialect"
    /**
     * DM
     */
    DM("dm"), //, "达梦数据库", "com.baomidou.mybatisplus.extension.plugins.pagination.dialects.DmDialect"
    /**
     * xugu
     */
    XUGU("xugu"), //, "虚谷数据库", "com.baomidou.mybatisplus.extension.plugins.pagination.dialects.XuguDialect"
    /**
     * UNKONWN DB
     */
    OTHER("other"); //, "其他数据库", "com.baomidou.mybatisplus.extension.plugins.pagination.dialects.UnknownDialect"

    /**
     * 数据库名称
     */
    private final String db=null;
    /**
     * 描述
     */
    private final String desc=null;

    /**
     * 分页方言
     */
    private String dialect;

    /**
     * 获取数据库类型（默认 MySql）
     *
     * @param dbType 数据库类型字符串
     */
    public static DbType getDbType(String dbType) {
        DbType[] dts = DbType.values();
        for (DbType dt : dts) {
            if (dt.getDb().equalsIgnoreCase(dbType)) {
                return dt;
            }
        }
        return OTHER;
    }
}
