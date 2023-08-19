package com.suber.provider2.controller;

import com.suber.common.entities.CommonResultCode;
import com.suber.provider2.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suber
 * 2023/8/19 11:18
 */
@RestController
@Slf4j
@RequestMapping("/provider2")
public class InfoController {
    @Autowired
    private PaymentService paymentService;


    @GetMapping("/info/info")
    public CommonResultCode getProvider2Info(){
        return new CommonResultCode(200,"调用provider2成功");
    }
}
