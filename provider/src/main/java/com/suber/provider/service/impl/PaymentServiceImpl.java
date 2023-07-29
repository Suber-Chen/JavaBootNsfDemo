package com.suber.provider.service.impl;

import com.suber.common.entities.CommonResultCode;
import com.suber.common.entities.Counter;
import com.suber.common.entities.Payment;
import com.suber.provider.dao.PaymentDao;
import com.suber.provider.service.PaymentService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Printable;
import java.util.concurrent.TimeUnit;

/**
 * @author suber
 * 2023/4/25 0:35
 */
@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }

    @Override
    public String paymentInfo() {
        return "provider 线程池: " + Thread.currentThread().getName() + "\t";
    }


    @Override
    public String paymentTimeout(Integer timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            log.error("timeout\t====》\t超时时间输入有误");
            throw new RuntimeException(e);
        }
        return "provider 线程池: " + Thread.currentThread().getName() + " 耗时" + timeout + "秒\t";
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
        return "provider 线程池: " + Thread.currentThread().getName() + "剩余" + num + "次请求会触发错误\t";
    }

    @Override
    public String paymentFailMust() {
        log.error("provider 必现错误");
        int i = 1 / 0;
        return null;
    }


}
