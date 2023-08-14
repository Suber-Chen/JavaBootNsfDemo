package com.suber.provider.service.impl;

import com.suber.common.entities.Counter;
import com.suber.provider.service.ErrorService;
import com.suber.provider.util.MyException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author suber
 * 2023/8/12 17:34
 */
@Service
@Log4j2
public class ErrorServiceImpl implements ErrorService {

    @Override
    public String paymentTimeout(Integer timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            log.error("timeout\t====》\t超时时间输入有误");
            throw new RuntimeException(e);
        }
        return "provider 线程池: " + Thread.currentThread().getName() + " 耗时" + timeout + "秒";
    }


    Counter counter = new Counter();

    @Override
    public String paymentFail() {
        int num = counter.getNumber();
        num--;
        counter.setNumber(num);
        log.info("num = " + num);
        if (num == 0) {
            counter.setNumber(5);
            int j = 1 / 0;
        }
        return "provider 线程池: " + Thread.currentThread().getName() + "剩余" + num + "次请求会触发错误";
    }

    @Override
    public String paymentFailMust() throws MyException {
            log.error("provider 必现错误。。。");
        try {
            int a = 1/0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
