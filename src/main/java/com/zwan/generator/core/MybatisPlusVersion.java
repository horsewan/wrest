package com.zwan.generator.core;

/**
 * 获取Mybatis-Plus版本
 *
 * @author nieqiurong 2018/11/13.
 */
public class MybatisPlusVersion {

    private MybatisPlusVersion() {
    }

    public static String getVersion() {
        Package pkg = MybatisPlusVersion.class.getPackage();
        return (pkg != null ? pkg.getImplementationVersion() : null);
    }

}
