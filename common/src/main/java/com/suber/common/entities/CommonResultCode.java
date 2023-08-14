package com.suber.common.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;
import javax.sound.sampled.Port;
import javax.xml.ws.Holder;

/**
 * @author suber
 * 2023/4/25 0:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResultCode <T>{
    private Integer code;
    private String message;
    private T data;
    private String host;

    public CommonResultCode(Integer code,String message){

        this(code,
                message,
                null,
                (((ServletRequestAttributes) RequestContextHolder
                        .getRequestAttributes())
                        .getRequest())
                        .getLocalAddr()+":"+(((ServletRequestAttributes) RequestContextHolder
                        .getRequestAttributes())
                        .getRequest())
                        .getServerPort()
                );
    }

    public CommonResultCode(Integer code,String message, T payment) {
        this(code,message, payment,(((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getRequest())
                .getLocalAddr()+":"+(((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getRequest())
                .getServerPort());
    }
}
