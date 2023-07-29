package com.suber.provider.controller;

import com.suber.common.entities.CommonResultCode;
import com.suber.provider.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suber
 * 2023/6/6 0:25
 */
@RestController
@Slf4j
@RequestMapping(("/provider/error"))
public class ErrorController {
    @Autowired
    private PaymentService paymentService;
    @GetMapping("/timeout")
    public CommonResultCode paymentTimeout(@RequestParam(value = "timeout", defaultValue = "3") Integer timeout) {

        String s = paymentService.paymentTimeout(timeout);
        log.error("provider 超时方法，超时时间："+timeout);
        return new CommonResultCode(500, "provider 超时方法", s);
    }
    @GetMapping("/error")
    public CommonResultCode paymentError() {
        String s = paymentService.paymentFail();
        return new CommonResultCode(200, "provider 5 次 请求 触发错误", s);
    }

    @GetMapping("/fail")
    public CommonResultCode paymentErrorMust() {
        String s = paymentService.paymentFailMust();
        return new CommonResultCode(200, "必然触发错误", s);
    }
}
