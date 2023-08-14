package com.suber.provider.service.impl;

import com.suber.common.entities.Payment;
import com.suber.provider.mapper.PaymentMapper;
import com.suber.provider.service.MysqlService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author suber
 * 2023/8/12 16:40
 */
@Service
@Log4j2
public class MysqlServiceImpl implements MysqlService {

    @Resource
    PaymentMapper paymentMapper;
    @Override
    public int insertPayment(Payment payment) {
        Payment payment1 = paymentMapper.selectById(payment.getId());
        if (payment1 ==null){
            log.error("插入失败，用户已存在");
            return -1;
        }
        log.info("provider 开始插入mysql...");
        int insert = 0;
        try {
            insert = paymentMapper.insert(payment);
        } catch (Exception e) {
            log.error("provider 插入mysql 异常，用户已存在");
            throw new RuntimeException(e);
        }
        if(insert>0){
            log.info("provider 插入mysql 成功");
        }else {
            log.warn("provider 插入mysql 失败");
        }
        return insert;
    }

    @Override
    public int deletePayment(Long id) {
        log.info("provider mysql开始删除payment...");
        int delete = 0;
        try {
        delete = paymentMapper.deleteById(id);
    } catch (Exception e) {
        log.error("provider mysql删除payment 异常");
        throw new RuntimeException(e);
    }
        if(delete>0){
        log.info("provider mysql删除payment 成功");
    }else {
        log.warn("provider mysql删除payment 失败");
    }
        return delete;
    }

    @Override
    public Payment selectPaymentById(Long id) {
        log.info("provider 开始查询mysql...");
        Payment payment = null;
        try {
            payment = paymentMapper.selectById(id);
        } catch (Exception e) {
            log.error("provider 查询异常...");
            throw new RuntimeException(e);
        }
        log.info("provider 查询mysql结束...");
        return payment;
    }

    @Override
    public int updatePaymentById(Payment payment) {
        log.info("provider 开始更新mysql...");
        int update = 0;
        try {
            update = paymentMapper.updateById(payment);
        } catch (Exception e) {
            log.error("provider 更新异常...");
            throw new RuntimeException(e);
        }
        log.info("provider 更新mysql结束...");
        return update;
    }
}
