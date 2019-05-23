package com.base.common.dto.response;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liuyanting
 * @description 表格-列表查询req
 * @date: 2019/02/26
 */
@Data
public class TableReqDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /*
     * 页码
     */
    private int current = 1;
    /*
     * 每页条数
     */
    private int size = 10;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
