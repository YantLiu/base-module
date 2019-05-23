package com.base.dto;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * 租户信息
 *
 * @author liuyanting
 * @email
 * @date 2019-02-22 19:25:19
 */
@Data
public class TenantDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	/*
     *
     */
    private Long id;

    /*
     *企业ID
     */
    private Long enterpriseId;

    /*
     *企业全局编号
     */
    private String enterpriseNumber;

    /*
     *企业名称
     */
    private String enterpriseName;

    /*
     *联系人姓名
     */
    private String contactPerson;

    /*
     *联系电话
     */
    private String mobilePhone;

    /*
     *邮箱地址
     */
    private String email;

    /*
     *租户ID
     */
    private Long tenantId;

    /*
     * 注册时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date registerTime;


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
