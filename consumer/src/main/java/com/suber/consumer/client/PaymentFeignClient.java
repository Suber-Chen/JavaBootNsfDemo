package com.suber.consumer.client;

import com.suber.common.entities.CommonResultCode;
import com.suber.common.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author suber
 * 2023/5/6 0:44
 */
@Component
@FeignClient(value = "provider")
@RequestMapping("/provider")
public interface PaymentFeignClient {
    @GetMapping("/info/info")
    public CommonResultCode providerInfo();
    @GetMapping("/info/provider2")
    public CommonResultCode provider2Info();

    @GetMapping("/error/error")
    public CommonResultCode providerError();

    @GetMapping("/error/timeout")
    public CommonResultCode providerTimeout(@RequestParam(value = "timeout") Integer timeout);

    @GetMapping("/error/fail")
    public CommonResultCode providerErrorMust();

    @PostMapping("/mysql/insert")
    public CommonResultCode InsertPayment(@RequestBody Payment payment);

    @DeleteMapping("/mysql/delete")
    public CommonResultCode deletePaymentById(@RequestParam(value = "id") Long id);

    @PostMapping("/mysql/update")
    public CommonResultCode updatePayment(@RequestParam(value = "id") Long id);

    @GetMapping("/mysql/select")
    public CommonResultCode selectPaymentById(@RequestParam(value = "id") Long id);

    @GetMapping("/router/header")
    public CommonResultCode getHeader(@RequestParam(value = "request")HttpServletRequest request, @RequestParam(value = "response")HttpServletResponse response);

    @GetMapping("/router/cookie")
    public CommonResultCode getCookie(@RequestParam(value = "request")HttpServletRequest request, @RequestParam(value = "response")HttpServletResponse response);

    @GetMapping("/router/param")
    public CommonResultCode getParam(@RequestParam(value = "request")HttpServletRequest request, @RequestParam(value = "response")HttpServletResponse response);
}
