package com.suber.provider.controller;

import com.suber.common.entities.CommonResultCode;
import com.suber.common.entities.Payment;
import com.suber.provider.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author suber
 * 2023/4/25 0:38
 */
@RestController
@Slf4j
@RequestMapping("/provider/info")
public class InfoController {
    @Autowired
    private PaymentService paymentService;



    @GetMapping("/info")
    public CommonResultCode paymentInfo() {

        String s = paymentService.paymentInfo();

        return new CommonResultCode(200, "查询成功！", s);
    }



    public CommonResultCode<Payment> consumerFailOver(){
        return new CommonResultCode<>(200,"容错方法，来自consumer");
    }

}
