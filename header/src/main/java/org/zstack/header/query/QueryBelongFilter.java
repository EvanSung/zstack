package org.zstack.header.query;

import java.util.List;

/**
 * Created by mingjian.deng on 2020/3/6.
 */
public interface QueryBelongFilter {
    String filterName();
    void filter(List result, String condition);
}
