package com.suber.consumer.controller;

import com.suber.common.entities.CommonResultCode;
import com.suber.common.entities.Payment;
import com.suber.consumer.client.PaymentFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author suber
 * 2023/4/25 3:20
 */
@RestController
@Slf4j
@RequestMapping("/consumer/info")
public class InfoController {

    @Autowired
    private DiscoveryClient discoveryClient;
    private String serviceID = "PROVIDER";

    int counter = 0;

    @Autowired
    private RestTemplate restTemplate;
    private static final String REMOTE_ADDR = "http://PROVIDER";

    /**
     * openfeign方式调用
     */
    @Resource
    private PaymentFeignClient client;

    /**
     * 服务发现
     * 获取eureka注册的 provider 服务的 服务实例
     *
     * @return
     */
    @GetMapping("/info")
    public CommonResultCode getInfo() {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceID);
        for (ServiceInstance instance : instances) {
            log.info("获取到" + serviceID + " : " + instance);
        }
        return new CommonResultCode(200, "success", instances);
    }

    /**
     * 反馈consumer的info信息
     * @return
     */
    @GetMapping("/consumerInfo")
    public CommonResultCode consumerInfo(){
        log.info("调用consumer consumerInfo接口");
        return new CommonResultCode<>(200,"consumer 线程池: " + Thread.currentThread().getName() +" : " +counter++);
    }

    /**
     * feign 调用接口，返回provider info的基本信息
     * 调用provider 的 info接口
     * @return
     */
    @GetMapping("/providerInfo/feign")
    public CommonResultCode<Payment> providerInfo() {
        log.info("调用provider info接口...");
        return client.providerInfo();
    }

    /**
     * restTemplate 调用接口，返回provider info的基本信息
     * 调用provider 的 info接口
     * @return
     */

    @GetMapping("/providerInfo/restTemplate")
    public CommonResultCode<Payment> providerInfo2() {
        log.info("调用provider info接口...");
        return restTemplate.getForObject(REMOTE_ADDR + "/provider/info/info", CommonResultCode.class);
    }


    @GetMapping("/consumerClear")
    public CommonResultCode<Payment> consumerClear() {
        log.info("调用consumer consumerClear接口...");
        counter = 0;
        return new CommonResultCode<>(200,"归零 consumer 计数器");
    }

}