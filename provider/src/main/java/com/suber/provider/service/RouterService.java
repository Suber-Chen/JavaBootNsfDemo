package com.suber.provider.service;

import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author suber
 * 2023/8/12 15:46
 */
public interface RouterService {
    public Map<String, Object> getHeader(@RequestParam("request")HttpServletRequest request, @RequestParam("response")HttpServletResponse response);
    public Cookie[]  getCookie(@RequestParam("request")HttpServletRequest request, @RequestParam("response")HttpServletResponse response);
    public Map<String, Object> getParam(@RequestParam("request")HttpServletRequest request, @RequestParam("response")HttpServletResponse response);
}
