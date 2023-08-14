package com.suber.provider.controller;

import com.suber.common.entities.CommonResultCode;
import com.suber.provider.service.ErrorService;
import com.suber.provider.util.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author suber
 * 2023/6/6 0:25
 */
@RestController
@Slf4j
@RequestMapping(("/provider/error"))
public class ErrorController {
    @Autowired
    private ErrorService errorService;

    /**
     * 超时方法 provider 会默认静默3秒返回结果。
     * @param timeout 可以指定静默时长
     * @return
     */
    @GetMapping("/timeout")
    public CommonResultCode paymentTimeout(@RequestParam(value = "timeout", defaultValue = "3") Integer timeout) {

        String s = errorService.paymentTimeout(timeout);
        log.error("provider 超时方法，超时时间："+timeout);
        return new CommonResultCode(500, s);
    }

    /**
     * provider 5 次 请求 触发错误
     * @return
     */
    @GetMapping("/error")
    public CommonResultCode paymentError() {
        String s = errorService.paymentFail();
        return new CommonResultCode(500, s);
    }

    /**
     * 必然触发错误
     * @return
     */
    @GetMapping("/fail")
    public CommonResultCode paymentErrorMust() throws MyException {
        String s = null;
        try {
            s = errorService.paymentFailMust();
        } finally {
            return new CommonResultCode(500, s,new MyException(500,"必现错误接口").getErrorMsg());
        }
    }

    public CommonResultCode consumerFailOver(){
        return new CommonResultCode<>(200,"容错方法，来自consumer");
    }
}
