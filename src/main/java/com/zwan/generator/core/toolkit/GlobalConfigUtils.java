package com.zwan.generator.core.toolkit;


import com.zwan.generator.annotation.IdType;
import com.zwan.generator.core.MybatisConfiguration;
import com.zwan.generator.core.config.GlobalConfig;
import com.zwan.generator.core.handlers.MetaObjectHandler;
import com.zwan.generator.core.incrementer.IKeyGenerator;
import com.zwan.generator.core.injector.DefaultSqlInjector;
import com.zwan.generator.core.injector.ISqlInjector;
import com.zwan.generator.core.metadata.TableInfo;
import com.zwan.generator.core.metadata.TableInfoHelper;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Set;

/**
 * Mybatis全局缓存工具类
 *
 * @author Caratacus
 * @since 2017-06-15
 */
public class GlobalConfigUtils {

    /**
     * 获取当前的SqlSessionFactory
     *
     * @param clazz 实体类
     */
    public static SqlSessionFactory currentSessionFactory(Class<?> clazz) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(clazz);
        Assert.notNull(tableInfo, ClassUtils.getUserClass(clazz).getName() + " Not Found TableInfoCache.");
        return tableInfo.getConfiguration().getGlobalConfig().getSqlSessionFactory();
    }

    /**
     * 获取默认 MybatisGlobalConfig
     * <p>FIXME 这可能是一个伪装成单例模式的原型模式，暂时不确定</p>
     */
    public static GlobalConfig defaults() {
        return new GlobalConfig().setDbConfig(new GlobalConfig.DbConfig());
    }

    /**
     * 获取MybatisGlobalConfig (统一所有入口)
     *
     * @param configuration Mybatis 容器配置对象
     */
    public static GlobalConfig getGlobalConfig(Configuration configuration) {
        Assert.notNull(configuration, "Error: You need Initialize MybatisConfiguration !");
        return ((MybatisConfiguration) configuration).getGlobalConfig();
    }

    public static IKeyGenerator getKeyGenerator(Configuration configuration) {
        return getGlobalConfig(configuration).getDbConfig().getKeyGenerator();
    }

    public static IdType getIdType(Configuration configuration) {
        return getGlobalConfig(configuration).getDbConfig().getIdType();
    }

    public static ISqlInjector getSqlInjector(Configuration configuration) {
        // fix #140
        GlobalConfig globalConfiguration = getGlobalConfig(configuration);
        ISqlInjector sqlInjector = globalConfiguration.getSqlInjector();
        if (sqlInjector == null) {
            sqlInjector = new DefaultSqlInjector();
            globalConfiguration.setSqlInjector(sqlInjector);
        }
        return sqlInjector;
    }

    public static MetaObjectHandler getMetaObjectHandler(Configuration configuration) {
        return getGlobalConfig(configuration).getMetaObjectHandler();
    }

    public static Class<?> getSuperMapperClass(Configuration configuration) {
        return getGlobalConfig(configuration).getSuperMapperClass();
    }

    public static boolean isSupperMapperChildren(Configuration configuration, Class<?> mapperClass) {
        return getSuperMapperClass(configuration).isAssignableFrom(mapperClass);
    }

    public static Set<String> getMapperRegistryCache(Configuration configuration) {
        return getGlobalConfig(configuration).getMapperRegistryCache();
    }
}
