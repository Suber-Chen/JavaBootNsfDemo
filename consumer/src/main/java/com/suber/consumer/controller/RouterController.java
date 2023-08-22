package com.suber.consumer.controller;

import com.suber.common.entities.CommonResultCode;
import com.suber.consumer.client.PaymentFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author suber
 * 2023/6/6 0:41
 */





// 透传有问题，不能如此透传
@Slf4j
@RestController
@RequestMapping("/consumer/router")
public class RouterController {
    @Resource
    PaymentFeignClient client;

    @GetMapping("/header")
    public CommonResultCode getHeaderController(HttpServletRequest request, HttpServletResponse response){
        log.info("调用provider getHeader");
        return client.getHeader(request,response);
    }

    @GetMapping("/cookie")
    public CommonResultCode getCookieController(HttpServletRequest request, HttpServletResponse response){
        log.info("调用provider getCookie");
        return client.getCookie(request,response);
    }

    @GetMapping("/param")
    public CommonResultCode getParamController(HttpServletRequest request, HttpServletResponse response){
        log.info("调用provider getParam");
        return client.getParam(request,response);
    }

}
