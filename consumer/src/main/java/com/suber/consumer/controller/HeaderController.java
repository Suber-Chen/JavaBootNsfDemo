package com.suber.consumer.controller;

import com.suber.common.entities.CommonResultCode;
import com.suber.consumer.client.PaymentFeignClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Log4j2
@RequestMapping("/consumer/header")
public class HeaderController extends HttpServlet {
    @Autowired
    PaymentFeignClient paymentFeignClient;

    @GetMapping("/cookie")
    public CommonResultCode getCookie(HttpServletRequest request, HttpServletResponse response){
        log.info("请求provider /provider/header/cookie 接口");
        return paymentFeignClient.getCookie(request, response);
    }
    @GetMapping("/header")
    public CommonResultCode getHeader(HttpServletRequest request, HttpServletResponse response){
        log.info("请求provider /provider/header/header 接口");
        return paymentFeignClient.getHeader(request, response);
    }
    @GetMapping("/param")
    public CommonResultCode getParam(HttpServletRequest request, HttpServletResponse response){
        log.info("请求provider /provider/header/param 接口");
        return paymentFeignClient.getParam(request, response);
    }

}
