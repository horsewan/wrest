package com.zwan.generator.config;



import com.zwan.generator.config.builder.ConfigBuilder;
import com.zwan.generator.config.rules.FileType;

import java.io.File;

/**
 * 文件覆盖接口
 *
 * @author hubin
 * @since 2018-08-07
 */
public interface IFileCreate {

    /**
     * 自定义判断是否创建文件
     *
     * @param configBuilder 配置构建器
     * @param fileType      文件类型
     * @param filePath      文件路径
     * @return ignore
     */
    boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath);

    /**
     * 检查文件目录，不存在自动递归创建
     *
     * @param filePath 文件路径
     */
    default void checkDir(String filePath) {
        File file = new File(filePath);
        boolean exist = file.exists();
        if (!exist) {
            file.getParentFile().mkdir();
        }
    }
}
