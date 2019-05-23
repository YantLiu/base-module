package com.base.api.enterprise;

import com.base.common.dto.response.BaseRespDTO;
import com.base.dto.TenantDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author liuyanting
 * @description 企业管理系统
 * @date: 2019/03/08
 */
@FeignClient(value = "${api.scf-enterprise.serviceId:scf-enterprise}")
public interface TenantService {
    /**
     * @param enterpriseName 企业名称
     * @return com.alt.base.common.dto.response.BaseRespDTO
     * @description 按企业名称查找租户(for华龙)
     * @author liuyanting
     * @date 2019/3/6
     */
    @GetMapping("/rpc/tenant/selectByEnterpriseName")
    BaseRespDTO<TenantDTO> selectTenantByEnterpriseName(@RequestParam("enterpriseName") String enterpriseName);

    /**
     * @description 查找租户信息
     * @param tenantId 租户ID
     * @return com.alt.base.common.dto.response.BaseRespDTO
     * @author liuyanting
     * @date 2019/3/6
     */
    @GetMapping("/rpc/tenant/selectTenantByTenantId")
    BaseRespDTO<TenantDTO> selectTenantByTenantId(@RequestParam("tenantId") Long tenantId);
}
