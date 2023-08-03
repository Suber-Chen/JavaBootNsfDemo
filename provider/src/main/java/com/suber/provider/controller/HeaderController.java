package com.suber.provider.controller;

import com.suber.common.entities.CommonResultCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@RestController
@Log4j2
@RequestMapping("/provider/header")
public class HeaderController extends HttpServlet {

    @Value("${provider.cookie.maxAge}")
    int maxAge;


    @GetMapping("/cookie")
    public CommonResultCode getCookie(HttpServletRequest request, HttpServletResponse response) {
        javax.servlet.http.Cookie[] cookies = request.getCookies();
        for (javax.servlet.http.Cookie cookie : cookies) {
            System.out.println("Cookie Value: " + cookie.getValue());
            System.out.println("Cookie Max Age: " + cookie.getMaxAge() + " seconds");
            System.out.println("Cookie Name: " + cookie.getName());
            if (cookie.getMaxAge() < 1) {
                Cookie cookie1 = new Cookie(cookie.getName(),cookie.getValue());
                cookie.setMaxAge(maxAge);
                response.addCookie(cookie1);
            }
        }
        return new CommonResultCode(200,"请求provider getCookie", cookies);
    }

    @GetMapping("/header")
    public CommonResultCode getHeader(HttpServletRequest request, HttpServletResponse response) {
//        获取header 中的 key
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
//            遍历enum，逐个获取header
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
        }
        return null;
    }

    @GetMapping("/param")
    public CommonResultCode getParam() {
        return null;
    }

}
