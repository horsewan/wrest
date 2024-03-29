package com.zwan.generator.core.enums;


import com.zwan.generator.core.conditions.ISqlSegment;
import com.zwan.generator.core.toolkit.StringPool;

/**
 * wrapper 内部使用枚举
 *
 * @author miemie
 * @since 2018-07-30
 */
public enum WrapperKeyword implements ISqlSegment {
    /**
     * 只用作于辨识,不用于其他
     */
    APPLY(null),
    LEFT_BRACKET(StringPool.LEFT_BRACKET),
    RIGHT_BRACKET(StringPool.RIGHT_BRACKET);

    private final String keyword;

    WrapperKeyword(final String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String getSqlSegment() {
        return keyword;
    }
}
