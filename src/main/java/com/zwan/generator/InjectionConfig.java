package com.zwan.generator;

import com.zwan.generator.config.FileOutConfig;
import com.zwan.generator.config.IFileCreate;
import com.zwan.generator.config.builder.ConfigBuilder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * 抽象的对外接口
 *
 * @author hubin
 * @since 2016-12-07
 */
@Data
@Accessors(chain = true)
public abstract class InjectionConfig {

    /**
     * 全局配置
     */
    private ConfigBuilder config;

    /**
     * 自定义返回配置 Map 对象
     */
    private Map<String, Object> map;

    /**
     * 自定义输出文件
     */
    private List<FileOutConfig> fileOutConfigList;

    /**
     * 自定义判断是否创建文件
     */
    private IFileCreate fileCreate;

    /**
     * 注入自定义 Map 对象
     */
    public abstract void initMap();

    /**
     * 模板待渲染 Object Map 预处理<br>
     * com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine
     * 方法： getObjectMap 结果处理
     */
    public Map<String, Object> prepareObjectMap(Map<String, Object> objectMap) {
        return objectMap;
    }
}
