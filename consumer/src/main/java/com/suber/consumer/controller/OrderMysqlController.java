package com.suber.consumer.controller;

import com.suber.common.entities.CommonResultCode;
import com.suber.common.entities.Payment;
import com.suber.consumer.client.PaymentFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author suber
 * 2023/6/5 23:00
 */
@Slf4j
@RestController
@RequestMapping("/consumer/mysql")
public class OrderMysqlController {
    @Autowired
    private RestTemplate restTemplate;
    private static final String REMOTE_ADDR = "http://PROVIDER";


    /**
     * openfeign方式调用
     */
    @Resource
    private PaymentFeignClient client;


    /**
     * 通过id 查询provider中的数据, restTemp 使用get请求调用provider
     * 会通过provider查mysql数据库
     * @param id
     * @return
     */
    @GetMapping("/get")
    public CommonResultCode<Payment> getPaymentById(@RequestParam(value = "id") Long id) {
        log.info("调用provider get接口...");
        return restTemplate.getForObject(REMOTE_ADDR + "/provider/mysql/get?id={id}", CommonResultCode.class, id);
    }


    /**
     * 插入新的数据  restTemp 使用post请求调用provider
     * 通过provider查mysql数据库
     * @param payment
     * @return
     */
    @GetMapping("/create")
    public CommonResultCode<Payment> create(@RequestBody Payment payment) {
        log.info("调用provider create接口...");
        return restTemplate.postForObject(REMOTE_ADDR + "/provider/mysql/create", payment, CommonResultCode.class);
    }

    /**
     * 通过id 查询provider中的数据, restTemp 使用getForEntity请求调用provider
     * getForEntity 返回的结果会携带一些请求头等的更为详细的信息(相应头、状态码、响应体)
     * 通过provider查mysql数据库
     * @param id
     * @return
     */
    @GetMapping("/getEntity")
    public CommonResultCode<Payment> getEntity(@RequestParam(value = "id") Long id) {
        ResponseEntity<CommonResultCode> forEntity = restTemplate.getForEntity(REMOTE_ADDR + "/provider/mysql/get?id={id}", CommonResultCode.class, id);
        if (forEntity.getStatusCode().is2xxSuccessful()) {
            log.info(String.valueOf(forEntity.getBody()));
            return forEntity.getBody();
        } else {
            log.error(String.valueOf(forEntity.getBody()));
            return new CommonResultCode<>(502, "服务异常...");
        }
    }

    /**
     * 通过id 查询provider中的数据, restTemp 使用getForEntity请求调用provider
     * postForEntity 返回的结果会携带一些请求头等的更为详细的信息(相应头、状态码、响应体)
     * 通过provider查mysql数据库
     * @param payment
     * @return
     */
    @GetMapping("/createEntity")
    public CommonResultCode<Payment> createEntity(@RequestBody Payment payment) {
        ResponseEntity<CommonResultCode> body = restTemplate.postForEntity(REMOTE_ADDR + "/provider/mysql/create", payment, CommonResultCode.class);
        if (body.getStatusCode().is2xxSuccessful()) {
            log.info(String.valueOf(body.getBody()));
            return body.getBody();
        } else {
            log.error(String.valueOf(body.getBody()));
            return new CommonResultCode<>(502, "服务异常...");
        }

    }


    /**
     * 通过id 查询provider中的数据, openFeign 使用get请求调用provider
     * 通过provider查mysql数据库
     * @param id
     * @return
     */
    @GetMapping("/getFeign")
    public CommonResultCode<Payment> getFeign(@RequestParam(value = "id") Long id) {
        log.info("调用provider get接口...");
        return client.getPaymentById(id);
    }


    /**
     * 插入新的数据  openFeign 使用post请求调用provider
     * 通过provider查mysql数据库
     * @param payment
     * @return
     */
    @GetMapping("/createFeign")
    public CommonResultCode<Payment> createFeign(@RequestBody Payment payment) {
        log.info("调用provider create接口...");
        return client.create(payment);
    }
}
