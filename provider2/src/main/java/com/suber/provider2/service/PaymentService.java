package com.suber.provider2.service;



import com.suber.common.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author suber
 * 2023/4/25 0:34
 */
public interface PaymentService {
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);
}
