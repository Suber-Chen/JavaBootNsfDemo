package com.suber.provider.service.impl;

import com.suber.common.entities.CommonResultCode;
import com.suber.common.entities.Counter;
import com.suber.common.entities.Payment;
import com.suber.provider.dao.PaymentDao;
import com.suber.provider.mapper.PaymentMapper;
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

//    @Autowired
//    private PaymentDao paymentDao;

    @Autowired
    private PaymentMapper paymentMapper;
//    @Override
//    public int create(Payment payment) {
//        return paymentDao.create(payment);
//    }

    @Override
    public String paymentInfo() {
        return "provider 线程池: " + Thread.currentThread().getName() + "\t";
    }
}
