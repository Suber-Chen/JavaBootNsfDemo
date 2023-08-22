package com.suber.provider2.controller;

import com.suber.common.entities.CommonResultCode;
import com.suber.common.entities.Payment;
import com.suber.provider2.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * @author suber
 * 2023/4/25 0:38
 */
@RestController
@Slf4j
@RequestMapping("/provider2")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;




    @PostMapping("/create")
    public CommonResultCode create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);

        if (result > 0) {
            log.info("插入结果：" + payment);
            return new CommonResultCode<>(200,  "\t:插入数据库成功！", payment);
        } else {
            log.error("插入数据库失败");
            return new CommonResultCode(500, "插入数据库失败！");
        }

    }

    @GetMapping("/get")
    public CommonResultCode getPaymentById(@RequestParam(value = "id") Long id) {

        Payment paymentById = paymentService.getPaymentById(id);
        log.info("开始查询：" + paymentById);

        if (paymentById != null) {
            log.info("查询到结果:" );
            return new CommonResultCode<>(200, "查询到结果:" , paymentById);
        } else {
            log.error("查询不到结果");
            return new CommonResultCode(500, "未查询到结果！");
        }

    }

    @GetMapping("/timeout")
    public CommonResultCode timeout() {

        try {
            TimeUnit.SECONDS.sleep(3);
            return new CommonResultCode(200, "provider处理请求耗时3秒...", new
                    SimpleDateFormat("yyyy-MM-dd :hh:mm:ss").format(new Date()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
