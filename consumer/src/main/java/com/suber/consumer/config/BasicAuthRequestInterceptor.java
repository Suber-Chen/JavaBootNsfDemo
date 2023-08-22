package com.suber.consumer.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Log4j2
public class BasicAuthRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        if (attributes != null) {

//            拦截透传header
            Enumeration<String> headerNames = request.getHeaderNames();
            log.info("开始打印本次请求头...");

            while(headerNames.hasMoreElements()){
                String headName = headerNames.nextElement();
                String headValue = request.getHeader(headName);
                log.info("透传header:{}={}",headName,headValue );
                requestTemplate.header(headName, headValue);
            }

//          拦截透传parameter
            Enumeration<String> paramsNames = request.getParameterNames();

            while (paramsNames.hasMoreElements()){
                String paramName = headerNames.nextElement();
                String[] parameterValues = request.getParameterValues(paramName);
                log.info("透传parameter:{}={}",paramName,parameterValues );
                requestTemplate.query(paramName,parameterValues);
            }


//            拦截透传cookie
            String cookie = request.getHeader("Cookie");
            if (!StringUtils.isEmpty(cookie)){
                log.info("透传Cookie:{}",cookie );
                requestTemplate.header("Cookie",cookie);
            }
        }
    }
}
