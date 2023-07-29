package com.suber.common.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public CommonResultCode(Integer code,String message){
        this(code,message,null);
    }
}
