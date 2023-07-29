package com.suber.consumer.controller;

import com.suber.common.entities.CommonResultCode;
import com.suber.common.entities.Payment;
import com.suber.consumer.client.PaymentFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author suber
 * 2023/6/5 23:32
 */

@RestController
@Slf4j
@RequestMapping("/consumer/error")
public class ErrorController {

    /**
     * openfeign方式调用
     */
    @Resource
    private PaymentFeignClient client;
    /**
     * 调用provider的timeout接口
     * 默认3s，可添加timeout参数自定义
     * @param timeout
     * @return
     */

    @GetMapping("/providerTimeout")
    public CommonResultCode<Payment> providerTimeout(@RequestParam(value = "timeout",defaultValue = "3") Integer timeout) {
        log.info("调用provider timeout接口...");
        return client.providerTimeout(timeout);
    }


    /**
     * 请求5次触发一次错误
     * 调用provider的error接口
     * @return
     */
    @GetMapping("/providerError")
    public CommonResultCode<Payment> providerError() {
        log.info("调用provider error接口...");
        return client.providerError();
    }

    /**
     * 每次请求都触发错误
     * 调用provider的fail接口
     * @return
     */

    @GetMapping("/providerFail")
    public CommonResultCode<Payment> providerFail() {
        log.info("调用provider fail接口...");
        return client.providerErrorMust();
    }


    public CommonResultCode<Payment> consumerFailOver(){
        return new CommonResultCode<>(200,"容错方法，来自consumer");
    }
}
