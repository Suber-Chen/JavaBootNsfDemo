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
    @GetMapping("/header/cookie")
    public CommonResultCode providerHeaderCookie(HttpServletRequest request,HttpServletResponse response);
    @GetMapping("/header/header")
    public CommonResultCode providerHeaderHeader(HttpServletRequest request,HttpServletResponse response);
    @GetMapping("/header/param")
    public CommonResultCode providerHeaderParam(HttpServletRequest request,HttpServletResponse response);
}
