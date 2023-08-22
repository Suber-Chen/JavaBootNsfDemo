package com.suber.provider.service;

import com.suber.common.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author suber
 * 2023/8/12 16:40
 */
public interface MysqlService {
    public int insertPayment(Payment payment);

    public int deletePayment(@Param("id") Long id);
    public int updatePaymentById(Payment payment);

    public Payment selectPaymentById(@Param("id") Long id);


}
