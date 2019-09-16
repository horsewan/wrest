package com.zwan.generator.core.conditions;

import com.zwan.generator.core.exceptions.MybatisPlusException;
import com.zwan.generator.core.toolkit.Assert;
import com.zwan.generator.core.toolkit.LambdaUtils;
import com.zwan.generator.core.toolkit.StringPool;
import com.zwan.generator.core.toolkit.support.ColumnCache;
import com.zwan.generator.core.toolkit.support.SFunction;
import com.zwan.generator.core.toolkit.support.SerializedLambda;
import org.apache.ibatis.reflection.property.PropertyNamer;

import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.joining;

/**
 * Lambda 语法使用 Wrapper
 * <p>统一处理解析 lambda 获取 column</p>
 *
 * @author hubin miemie HCL
 * @since 2017-05-26
 */
@SuppressWarnings("serial")
public abstract class AbstractLambdaWrapper<T, Children extends AbstractLambdaWrapper<T, Children>>
    extends AbstractWrapper<T, SFunction<T, ?>, Children> {

    private Map<String, ColumnCache> columnMap = null;
    private boolean initColumnMap = false;

    @Override
    protected void initEntityClass() {
        super.initEntityClass();
        if (entityClass != null) {
            columnMap = LambdaUtils.getColumnMap(entityClass);
            initColumnMap = true;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    protected String columnsToString(SFunction<T, ?>... columns) {
        return columnsToString(true, columns);
    }

    @SuppressWarnings("unchecked")
    protected String columnsToString(boolean onlyColumn, SFunction<T, ?>... columns) {
        return Arrays.stream(columns).map(i -> columnToString(i, onlyColumn)).collect(joining(StringPool.COMMA));
    }

    @Override
    protected String columnToString(SFunction<T, ?> column) {
        return columnToString(column, true);
    }

    protected String columnToString(SFunction<T, ?> column, boolean onlyColumn) {
        return getColumn(LambdaUtils.resolve(column), onlyColumn);
    }

    /**
     * 获取 SerializedLambda 对应的列信息，从 lambda 表达式中推测实体类
     * <p>
     * 如果获取不到列信息，那么本次条件组装将会失败
     *
     * @param lambda     lambda 表达式
     * @param onlyColumn 如果是，结果: "name", 如果否： "name" as "name"
     * @return 列
     * @throws com.zwan.generator.core.exceptions.MybatisPlusException 获取不到列信息时抛出异常
     * @see SerializedLambda#getImplClass()
     * @see SerializedLambda#getImplMethodName()
     */
    private String getColumn(SerializedLambda lambda, boolean onlyColumn) throws MybatisPlusException {
        String fieldName = PropertyNamer.methodToProperty(lambda.getImplMethodName());
        Class aClass = lambda.getInstantiatedMethodType();
        if (!initColumnMap) {
            columnMap = LambdaUtils.getColumnMap(aClass);
        }
        Assert.notNull(columnMap, "can not find lambda cache for this entity [%s]", aClass.getName());
        ColumnCache columnCache = columnMap.get(LambdaUtils.formatKey(fieldName));
        Assert.notNull(columnCache, "can not find lambda cache for this property [%s] of entity [%s]",
            fieldName, aClass.getName());
        return onlyColumn ? columnCache.getColumn() : columnCache.getColumnSelect();
    }
}
