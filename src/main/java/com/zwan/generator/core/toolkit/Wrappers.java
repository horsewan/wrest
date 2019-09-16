package com.zwan.generator.core.toolkit;



import com.zwan.generator.core.conditions.ISqlSegment;
import com.zwan.generator.core.conditions.query.LambdaQueryWrapper;
import com.zwan.generator.core.conditions.query.QueryWrapper;
import com.zwan.generator.core.conditions.segments.MergeSegments;
import com.zwan.generator.core.conditions.update.LambdaUpdateWrapper;
import com.zwan.generator.core.conditions.update.UpdateWrapper;

import java.util.Collections;
import java.util.Map;

/**
 * Wrapper 条件构造
 *
 * @author Caratacus
 */
public final class Wrappers {

    /**
     * 空的 EmptyWrapper
     */
    private static final QueryWrapper<?> emptyWrapper = new EmptyWrapper<>();

    private Wrappers() {
        // ignore
    }

    /**
     * 获取 QueryWrapper&lt;T&gt;
     *
     * @param <T> 实体类泛型
     * @return QueryWrapper&lt;T&gt;
     */
    public static <T> QueryWrapper<T> query() {
        return new QueryWrapper<>();
    }

    /**
     * 获取 QueryWrapper&lt;T&gt;
     *
     * @param entity 实体类
     * @param <T>    实体类泛型
     * @return QueryWrapper&lt;T&gt;
     */
    public static <T> QueryWrapper<T> query(T entity) {
        return new QueryWrapper<>(entity);
    }

    /**
     * 获取 LambdaQueryWrapper&lt;T&gt;
     *
     * @param <T> 实体类泛型
     * @return LambdaQueryWrapper&lt;T&gt;
     */
    public static <T> LambdaQueryWrapper<T> lambdaQuery() {
        return new LambdaQueryWrapper<>();
    }

    /**
     * 获取 LambdaQueryWrapper&lt;T&gt;
     *
     * @param entity 实体类
     * @param <T>    实体类泛型
     * @return LambdaQueryWrapper&lt;T&gt;
     */
    public static <T> LambdaQueryWrapper<T> lambdaQuery(T entity) {
        return new LambdaQueryWrapper<>(entity);
    }

    /**
     * 获取 UpdateWrapper&lt;T&gt;
     *
     * @param <T> 实体类泛型
     * @return UpdateWrapper&lt;T&gt;
     */
    public static <T> UpdateWrapper<T> update() {
        return new UpdateWrapper<>();
    }

    /**
     * 获取 UpdateWrapper&lt;T&gt;
     *
     * @param entity 实体类
     * @param <T>    实体类泛型
     * @return UpdateWrapper&lt;T&gt;
     */
    public static <T> UpdateWrapper<T> update(T entity) {
        return new UpdateWrapper<>(entity);
    }

    /**
     * 获取 LambdaUpdateWrapper&lt;T&gt;
     *
     * @param <T> 实体类泛型
     * @return LambdaUpdateWrapper&lt;T&gt;
     */
    public static <T> LambdaUpdateWrapper<T> lambdaUpdate() {
        return new LambdaUpdateWrapper<>();
    }

    /**
     * 获取 LambdaUpdateWrapper&lt;T&gt;
     *
     * @param entity 实体类
     * @param <T>    实体类泛型
     * @return LambdaUpdateWrapper&lt;T&gt;
     */
    public static <T> LambdaUpdateWrapper<T> lambdaUpdate(T entity) {
        return new LambdaUpdateWrapper<>(entity);
    }

    /**
     * 获取 EmptyWrapper&lt;T&gt;
     *
     * @param <T> 任意泛型
     * @return EmptyWrapper&lt;T&gt;
     * @see EmptyWrapper
     */
    @SuppressWarnings("unchecked")
    public static <T> QueryWrapper<T> emptyWrapper() {
        return (QueryWrapper<T>) emptyWrapper;
    }

    /**
     * 一个空的QueryWrapper子类该类不包含任何条件
     *
     * @param <T>
     * @see com.zwan.generator.core.conditions.query.QueryWrapper
     */
    private static class EmptyWrapper<T> extends QueryWrapper<T> {

        private static final long serialVersionUID = -2515957613998092272L;

        @Override
        public T getEntity() {
            return null;
        }

        @Override
        public EmptyWrapper<T> setEntity(T entity) {
            throw new UnsupportedOperationException();
        }

        @Override
        public String getSqlSelect() {
            return null;
        }

        @Override
        public MergeSegments getExpression() {
            return null;
        }

        @Override
        public boolean isEmptyOfWhere() {
            return true;
        }

        @Override
        public boolean isEmptyOfNormal() {
            return true;
        }

        @Override
        public boolean nonEmptyOfEntity() {
            return !isEmptyOfEntity();
        }

        @Override
        public boolean isEmptyOfEntity() {
            return true;
        }

        @Override
        protected void initEntityClass() {
        }

        @Override
        protected Class<T> getCheckEntityClass() {
            throw new UnsupportedOperationException();
        }

        @Override
        public EmptyWrapper<T> last(boolean condition, String lastSql) {
            throw new UnsupportedOperationException();
        }

        @Override
        protected EmptyWrapper<T> doIt(boolean condition, ISqlSegment... sqlSegments) {
            throw new UnsupportedOperationException();
        }

        @Override
        public String getSqlSegment() {
            return null;
        }

        @Override
        public Map<String, Object> getParamNameValuePairs() {
            return Collections.emptyMap();
        }

        @Override
        protected String columnsToString(String... columns) {
            return null;
        }

        @Override
        protected String columnToString(String column) {
            return null;
        }

        @Override
        protected EmptyWrapper<T> instance() {
            throw new UnsupportedOperationException();
        }
    }
}
