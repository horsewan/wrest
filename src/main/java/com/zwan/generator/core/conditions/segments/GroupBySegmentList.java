package com.zwan.generator.core.conditions.segments;


import com.zwan.generator.core.conditions.ISqlSegment;

import java.util.List;

import static com.zwan.generator.core.enums.SqlKeyword.GROUP_BY;
import static java.util.stream.Collectors.joining;

/**
 * Group By SQL 片段
 *
 * @author miemie
 * @since 2018-06-27
 */
@SuppressWarnings("serial")
public class GroupBySegmentList extends AbstractISegmentList {

    @Override
    protected boolean transformList(List<ISqlSegment> list, ISqlSegment firstSegment) {
        list.remove(0);
        return true;
    }

    @Override
    protected String childrenSqlSegment() {
        if (isEmpty()) {
            return EMPTY;
        }
        return this.stream().map(ISqlSegment::getSqlSegment).collect(joining(COMMA, SPACE + GROUP_BY.getSqlSegment() + SPACE, EMPTY));
    }
}
