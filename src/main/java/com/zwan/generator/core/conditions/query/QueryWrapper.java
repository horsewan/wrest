package com.zwan.generator.core.conditions.query;


import com.zwan.generator.core.conditions.AbstractWrapper;
import com.zwan.generator.core.conditions.SharedString;
import com.zwan.generator.core.conditions.segments.MergeSegments;
import com.zwan.generator.core.metadata.TableFieldInfo;
import com.zwan.generator.core.metadata.TableInfoHelper;
import com.zwan.generator.core.toolkit.ArrayUtils;
import com.zwan.generator.core.toolkit.StringPool;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

/**
 * Entity 对象封装操作类
 *
 * @author hubin miemie HCL
 * @since 2018-05-25
 */
@SuppressWarnings("serial")
public class QueryWrapper<T> extends AbstractWrapper<T, String, QueryWrapper<T>>
    implements Query<QueryWrapper<T>, T, String> {

    /**
     * 查询字段
     */
    private SharedString sqlSelect = new SharedString();

    public QueryWrapper() {
        this(null);
    }

    public QueryWrapper(T entity) {
        super.setEntity(entity);
        super.initNeed();
    }

    public QueryWrapper(T entity, String... columns) {
        super.setEntity(entity);
        super.initNeed();
        this.select(columns);
    }

    /**
     * 非对外公开的构造方法,只用于生产嵌套 sql
     *
     * @param entityClass 本不应该需要的
     */
    private QueryWrapper(T entity, Class<T> entityClass, AtomicInteger paramNameSeq,
                         Map<String, Object> paramNameValuePairs, MergeSegments mergeSegments,
                         SharedString lastSql, SharedString sqlComment) {
        super.setEntity(entity);
        this.entityClass = entityClass;
        this.paramNameSeq = paramNameSeq;
        this.paramNameValuePairs = paramNameValuePairs;
        this.expression = mergeSegments;
        this.lastSql = lastSql;
        this.sqlComment = sqlComment;
    }

    @Override
    public QueryWrapper<T> select(String... columns) {
        if (ArrayUtils.isNotEmpty(columns)) {
            this.sqlSelect.setStringValue(String.join(StringPool.COMMA, columns));
        }
        return typedThis;
    }

    @Override
    public QueryWrapper<T> select(Predicate<TableFieldInfo> predicate) {
        return select(entityClass, predicate);
    }

    @Override
    public QueryWrapper<T> select(Class<T> entityClass, Predicate<TableFieldInfo> predicate) {
        this.entityClass = entityClass;
        this.sqlSelect.setStringValue(TableInfoHelper.getTableInfo(getCheckEntityClass()).chooseSelect(predicate));
        return typedThis;
    }

    @Override
    public String getSqlSelect() {
        return sqlSelect.getStringValue();
    }

    /**
     * 返回一个支持 lambda 函数写法的 wrapper
     */
    public LambdaQueryWrapper<T> lambda() {
        return new LambdaQueryWrapper<>(entity, entityClass, sqlSelect, paramNameSeq, paramNameValuePairs, expression,
            lastSql, sqlComment);
    }

    /**
     * 用于生成嵌套 sql
     * <p>
     * 故 sqlSelect 不向下传递
     * </p>
     */
    @Override
    protected QueryWrapper<T> instance() {
        return new QueryWrapper<>(entity, entityClass, paramNameSeq, paramNameValuePairs, new MergeSegments(),
            SharedString.emptyString(), SharedString.emptyString());
    }
}
