package com.suber.provider.controller;

import com.suber.common.entities.CommonResultCode;
import com.suber.provider.service.impl.RouterServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author suber
 * 2023/8/12 15:41
 */
@RestController
@RequestMapping("/provider/router")
public class RouterController {

    @Resource
    RouterServiceImpl routerService;

    /**
     * 获取header信息
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/header")
    public CommonResultCode getHeader(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> header = routerService.getHeader(request, response);
        return new CommonResultCode(200,"successful",header);
    }

    /**
     * 获取cookie信息
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/cookie")
    public CommonResultCode getCookie(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookie = routerService.getCookie(request, response);
        return new CommonResultCode(200,"successful",cookie);
    }

    /**
     * 获取参数信息
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/param")
    public CommonResultCode getParam(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> param = routerService.getParam(request, response);
        return new CommonResultCode(200,"successful",param);
    }
}
