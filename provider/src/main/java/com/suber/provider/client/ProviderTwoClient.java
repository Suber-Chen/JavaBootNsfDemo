package com.suber.provider.client;

import com.suber.common.entities.CommonResultCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author suber
 * 2023/8/19 11:10
 */

@Component
@FeignClient("provider2")
@RequestMapping("/provider2")
public interface ProviderTwoClient {
    @GetMapping("/info/info")
    public CommonResultCode getProvider2Info();
}
