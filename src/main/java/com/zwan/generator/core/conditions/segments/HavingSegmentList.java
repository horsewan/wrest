package com.zwan.generator.core.conditions.segments;


import com.zwan.generator.core.conditions.ISqlSegment;
import com.zwan.generator.core.enums.SqlKeyword;

import java.util.List;


import static com.zwan.generator.core.enums.SqlKeyword.HAVING;
import static java.util.stream.Collectors.joining;

/**
 * Having SQL 片段
 *
 * @author miemie
 * @since 2018-06-27
 */
@SuppressWarnings("serial")
public class HavingSegmentList extends AbstractISegmentList {

    @Override
    protected boolean transformList(List<ISqlSegment> list, ISqlSegment firstSegment) {
        if (!isEmpty()) {
            this.add(SqlKeyword.AND);
        }
        list.remove(0);
        return true;
    }

    @Override
    protected String childrenSqlSegment() {
        if (isEmpty()) {
            return EMPTY;
        }
        return this.stream().map(ISqlSegment::getSqlSegment).collect(joining(SPACE, SPACE + HAVING.getSqlSegment() + SPACE, EMPTY));
    }
}
