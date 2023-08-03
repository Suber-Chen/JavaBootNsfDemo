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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
                try {
                    cookie.setMaxAge(maxAge);
                    response.addCookie(cookie1);
                    log.info("set cookie {}={} , setMaxAge = {}",cookie.getName(),cookie.getValue(),maxAge);
                } catch (Exception e) {
                    log.error("set cookie {}={} , setMaxAge = {}",cookie.getName(),cookie.getValue(),maxAge);
                    throw new RuntimeException(e);
                }
            }
        }
        return new CommonResultCode(200,"请求provider getCookie", cookies);
    }

    @GetMapping("/header")
    public CommonResultCode getHeader(HttpServletRequest request, HttpServletResponse response) {
//        获取header 中的 key
        Enumeration<String> headerNames = request.getHeaderNames();
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        while (headerNames.hasMoreElements()) {
//            遍历enum，逐个获取header
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            objectObjectHashMap.put(headerName,headerValue);
            log.info("put {}={} into map ",headerName,headerValue);
        }
        return new CommonResultCode(200, "请求provider getHeader",objectObjectHashMap);
    }

    @GetMapping("/param")
    public CommonResultCode getParam(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        for (Map.Entry<String, String[]> entry:entries) {
            System.out.println(entry.getKey()+" = " +entry.getValue());
            log.info(entry.getKey()+" = " +entry.getValue());
        }
        return new CommonResultCode(200,"请求provider getParam",parameterMap);
    }

}
