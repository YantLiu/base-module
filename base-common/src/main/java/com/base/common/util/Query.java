package com.base.common.util;


import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 */
@Data
public class Query extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    private static final String CURRENT = "current";
    private static final String SIZE = "size";
    //当前页码
    private int page = 1;
    //每页条数
    private int limit = 10;

    public Query(Map<String, Object> params) {
        this.putAll(params);
        //分页参数
        if (params.get(CURRENT) != null) {
            this.page = Integer.parseInt(params.get(CURRENT).toString());
        }
        if (params.get(SIZE) != null) {
            this.limit = Integer.parseInt(params.get(SIZE).toString());
        }
        this.remove(CURRENT);
        this.remove(SIZE);
    }
}
