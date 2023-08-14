package com.suber.provider.controller;

import com.suber.common.entities.CommonResultCode;
import com.suber.common.entities.Payment;
import com.suber.provider.service.MysqlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private MysqlService mysqlService;


    @PostMapping("/insert")
    public CommonResultCode createPayment(@RequestBody Payment payment) {
        int result = mysqlService.insertPayment(payment);
        if (result > 0) {
            log.info("插入结果：" + payment);
            return new CommonResultCode<>(200,   "\t:插入数据库成功！", payment);
        } else {
            return new CommonResultCode(500, "插入数据库失败！用户已存在");
        }

    }

    @DeleteMapping("/delete")
    public CommonResultCode deletePayment(@RequestParam(value = "id") Long id){
        int result = mysqlService.deletePayment(id);

        if (result > 0) {
            log.info("删除成功");
            return new CommonResultCode<>(200,   "\t:删除数据库成功！",result);
        } else {
            return new CommonResultCode(500, "删除数据库失败！");
        }
    }

    @PostMapping("/update")
    public CommonResultCode updatePaymentById(@RequestBody Payment payment) {

        int i = mysqlService.updatePaymentById(payment);
        log.info("开始更新：" + payment);

        if (i > 0) {
            log.info("更新成功" );
            return new CommonResultCode(200, "更新数据库成功:" ,payment);
        } else {
            log.error("更新失败");
            return new CommonResultCode(500, "更新数据库失败！");
        }

    }

    @GetMapping("/select")
    public CommonResultCode getPaymentById(@RequestParam(value = "id") Long id) {

        Payment paymentById = mysqlService.selectPaymentById(id);
        log.info("开始查询：" + paymentById);

        if (paymentById != null) {
            return new CommonResultCode<>(200, "查询到结果:" , paymentById);
        } else {
            return new CommonResultCode(500, "未查询到结果！");
        }

    }
}
