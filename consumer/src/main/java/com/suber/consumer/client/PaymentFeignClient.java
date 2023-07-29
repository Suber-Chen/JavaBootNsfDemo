package com.suber.consumer.client;

import com.suber.common.entities.CommonResultCode;
import com.suber.common.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * @author suber
 * 2023/5/6 0:44
 */
@Component
@FeignClient(value = "PROVIDER")
@RequestMapping("/provider")
public interface PaymentFeignClient {
    @PostMapping("/mysql/create")
    public CommonResultCode create(@RequestBody Payment payment);

    @GetMapping("/mysql/get")
    public CommonResultCode getPaymentById(@RequestParam(value = "id") Long id);

    @GetMapping("/error/timeout")
    public CommonResultCode providerTimeout(@RequestParam(value = "timeout") Integer timeout);

    @GetMapping("/info/info")
    public CommonResultCode providerInfo();

    @GetMapping("/error/error")
    public CommonResultCode providerError();

    @GetMapping("/error/fail")
    public CommonResultCode providerErrorMust();
}
