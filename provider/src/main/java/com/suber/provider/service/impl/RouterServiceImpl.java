package com.suber.provider.service.impl;

import com.suber.provider.service.RouterService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author suber
 * 2023/8/12 15:49
 */
@Service
@Log4j2
public class RouterServiceImpl implements RouterService {
    @Override
    public Map<String, Object> getHeader(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> stringObjectHashMap = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        log.info("开始打印本次请求头...");
        while(headerNames.hasMoreElements()){
            String headName = (String)headerNames.nextElement();
            String headValue = request.getHeader(headName);
            System.out.println(headName + " : " + headValue);
            stringObjectHashMap.put(headName,headValue);
        }
        log.info("getHeader...");
        return stringObjectHashMap;
    }

    @Override
    public Cookie[] getCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        log.info("开始打印本次请求cookie...");
        for (Cookie cookie:cookies) {
            log.info(cookie.getName()+" : "+cookie.getValue());
        }
        log.info("getCookie请求完成...");

        return cookies;
    }

    @Override
    public Map<String, Object> getParam(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> stringObjectHashMap = new HashMap<>();
        Enumeration<String> headerNames = request.getParameterNames();
        log.info("开始打印本次请求参数...");
        while (headerNames.hasMoreElements()){
            String headName = (String)headerNames.nextElement();
            String[] parameterValues = request.getParameterValues(headName);
            stringObjectHashMap.put(headName,parameterValues);
        }
        log.info("getParam请求完成...");
        return stringObjectHashMap;
    }
}
