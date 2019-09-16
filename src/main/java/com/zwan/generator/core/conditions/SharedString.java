package com.zwan.generator.core.conditions;


import com.zwan.generator.core.toolkit.StringPool;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 共享查询字段
 *
 * @author miemie
 * @since 2018-11-20
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SharedString implements Serializable {
    private static final long serialVersionUID = -1536422416594422874L;

    /**
     * 共享的 string 值
     */
    private String stringValue;

    /**
     * SharedString 里是 ""
     */
    public static SharedString emptyString() {
        return new SharedString(StringPool.EMPTY);
    }
}
