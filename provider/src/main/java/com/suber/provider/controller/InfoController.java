package com.suber.provider.controller;

import com.suber.common.entities.CommonResultCode;
import com.suber.provider.client.ProviderTwoClient;
import com.suber.provider.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ProviderTwoClient providerTwoClient;

    @GetMapping("/info")
    public CommonResultCode paymentInfo() {

        String s = paymentService.paymentInfo();

        return new CommonResultCode(200, "查询成功！");
    }

    @GetMapping("/provider2")
    public CommonResultCode provider2Info() {
        return providerTwoClient.getProvider2Info();
    }



}
