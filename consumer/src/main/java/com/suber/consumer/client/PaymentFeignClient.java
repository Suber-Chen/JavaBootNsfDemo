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
    @GetMapping("/info/info")
    public CommonResultCode providerInfo();

    @GetMapping("/error/error")
    public CommonResultCode providerError();

    @GetMapping("/error/timeout")
    public CommonResultCode providerTimeout(@RequestParam(value = "timeout") Integer timeout);

    @PostMapping("/mysql/insert")
    public CommonResultCode InsertPayment(@RequestBody Payment payment);

    @GetMapping("/mysql/delete")
    public CommonResultCode deletePaymentById(@RequestParam(value = "id") Long id);

    @GetMapping("/mysql/update")
    public CommonResultCode updatePayment(@RequestParam(value = "id") Long id);

    @GetMapping("/mysql/select")
    public CommonResultCode selectPaymentById(@RequestParam(value = "id") Long id);

    @GetMapping("/error/fail")
    public CommonResultCode providerErrorMust();

    @GetMapping("/router/header")
    public CommonResultCode getHeader();

    @GetMapping("/router/cookie")
    public CommonResultCode getCookie();

    @GetMapping("/router/param")
    public CommonResultCode getParam();
}
