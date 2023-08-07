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
 * 2023/6/6 0:23
 */
@RestController
@Slf4j
@RequestMapping("/provider/mysql")
public class MysqlController {
    @Autowired
    private PaymentService paymentService;

//    @Value("${eureka.instance.instanceId}")
//    private String host;

    @PostMapping("/create")
    public CommonResultCode create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);

        if (result > 0) {
            log.info("插入结果：" + payment);
            log.info("插入数据库成功:" );
            return new CommonResultCode<>(200,   "\t:插入数据库成功！", payment);
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

    @GetMapping("/update")
    public CommonResultCode updatePaymentById(@RequestBody Payment payment) {

        int i = paymentService.updatePaymentById(payment);
        log.info("开始更新：" + payment);

        if (i > 0) {
            log.info("更新成功:" );
            return new CommonResultCode<>(200, "更新数据库成功:" , payment);
        } else {
            log.error("更新失败");
            return new CommonResultCode(500, "更新数据库失败！");
        }

    }
}
