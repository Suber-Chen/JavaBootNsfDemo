package com.suber.provider.util;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author suber
 * 2023/8/14 22:10
 */
@Data
@NoArgsConstructor
public class MyException extends Exception{
    Integer code;
    String errorMsg;
    public MyException(Integer code, String msg){
        this.code=code;
        this.errorMsg=msg;
    }
}
