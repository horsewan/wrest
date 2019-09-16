package com.zwan.generator.core.injector;


import com.zwan.generator.core.assist.ISqlRunner;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.Configuration;

import java.util.ArrayList;
import java.util.Map;

/**
 * SqlRunner 注入器
 *
 * @author hubin
 * @since 2018-04-08
 */
public class SqlRunnerInjector {

    private static final Log logger = LogFactory.getLog(SqlRunnerInjector.class);

    protected Configuration configuration;
    protected LanguageDriver languageDriver;


    public void inject(Configuration configuration) {
        this.configuration = configuration;
        this.languageDriver = configuration.getDefaultScriptingLanguageInstance();
        this.initSelectList();
        this.initSelectObjs();
        this.initInsert();
        this.initUpdate();
        this.initDelete();
        this.initCount();
    }

    /**
     * 是否已经存在MappedStatement
     *
     * @param mappedStatement
     * @return
     */
    private boolean hasMappedStatement(String mappedStatement) {
        return configuration.hasStatement(mappedStatement, false);
    }

    /**
     * 创建查询MappedStatement
     *
     * @param mappedStatement
     * @param sqlSource       执行的sqlSource
     * @param resultType      返回的结果类型
     */
    @SuppressWarnings("serial")
    private void createSelectMappedStatement(String mappedStatement, SqlSource sqlSource, final Class<?> resultType) {
        MappedStatement ms = new MappedStatement.Builder(configuration, mappedStatement, sqlSource, SqlCommandType.SELECT)
            .resultMaps(new ArrayList<ResultMap>() {
                {
                    add(new ResultMap.Builder(configuration, "defaultResultMap", resultType, new ArrayList<>(0))
                        .build());
                }
            }).build();
        // 缓存
        configuration.addMappedStatement(ms);
    }

    /**
     * 创建一个MappedStatement
     *
     * @param mappedStatement
     * @param sqlSource       执行的sqlSource
     * @param sqlCommandType  执行的sqlCommandType
     */
    @SuppressWarnings("serial")
    private void createUpdateMappedStatement(String mappedStatement, SqlSource sqlSource, SqlCommandType sqlCommandType) {
        MappedStatement ms = new MappedStatement.Builder(configuration, mappedStatement, sqlSource, sqlCommandType).resultMaps(
            new ArrayList<ResultMap>() {
                {
                    add(new ResultMap.Builder(configuration, "defaultResultMap", int.class, new ArrayList<>(0))
                        .build());
                }
            }).build();
        // 缓存
        configuration.addMappedStatement(ms);
    }

    /**
     * initSelectList
     */
    private void initSelectList() {
        if (hasMappedStatement(ISqlRunner.SELECT_LIST)) {
            logger.warn("MappedStatement 'SqlRunner.SelectList' Already Exists");
            return;
        }
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, ISqlRunner.SQL_SCRIPT, Map.class);
        createSelectMappedStatement(ISqlRunner.SELECT_LIST, sqlSource, Map.class);
    }

    /**
     * initSelectObjs
     */
    private void initSelectObjs() {
        if (hasMappedStatement(ISqlRunner.SELECT_OBJS)) {
            logger.warn("MappedStatement 'SqlRunner.SelectObjs' Already Exists");
            return;
        }
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, ISqlRunner.SQL_SCRIPT, Object.class);
        createSelectMappedStatement(ISqlRunner.SELECT_OBJS, sqlSource, Object.class);
    }

    /**
     * initCount
     */
    private void initCount() {
        if (hasMappedStatement(ISqlRunner.COUNT)) {
            logger.warn("MappedStatement 'SqlRunner.Count' Already Exists");
            return;
        }
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, ISqlRunner.SQL_SCRIPT, Map.class);
        createSelectMappedStatement(ISqlRunner.COUNT, sqlSource, Integer.class);
    }

    /**
     * initInsert
     */
    private void initInsert() {
        if (hasMappedStatement(ISqlRunner.INSERT)) {
            logger.warn("MappedStatement 'SqlRunner.Insert' Already Exists");
            return;
        }
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, ISqlRunner.SQL_SCRIPT, Map.class);
        createUpdateMappedStatement(ISqlRunner.INSERT, sqlSource, SqlCommandType.INSERT);
    }

    /**
     * initUpdate
     */
    private void initUpdate() {
        if (hasMappedStatement(ISqlRunner.UPDATE)) {
            logger.warn("MappedStatement 'SqlRunner.Update' Already Exists");
            return;
        }
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, ISqlRunner.SQL_SCRIPT, Map.class);
        createUpdateMappedStatement(ISqlRunner.UPDATE, sqlSource, SqlCommandType.UPDATE);
    }

    /**
     * initDelete
     */
    private void initDelete() {
        if (hasMappedStatement(ISqlRunner.DELETE)) {
            logger.warn("MappedStatement 'SqlRunner.Delete' Already Exists");
            return;
        }
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, ISqlRunner.SQL_SCRIPT, Map.class);
        createUpdateMappedStatement(ISqlRunner.DELETE, sqlSource, SqlCommandType.DELETE);
    }
}
