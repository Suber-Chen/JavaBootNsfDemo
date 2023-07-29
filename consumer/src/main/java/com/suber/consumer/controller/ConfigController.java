package com.suber.consumer.controller;

import com.suber.common.entities.CommonResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suber
 * 2023/6/5 23:36
 */
@RestController
@Slf4j
@RequestMapping("/consumer/config")
public class ConfigController {


    @Value("${config.name}")
    private String name;

    @Value("${config.age}")
    private String age;

    /**
     * 获取consumer的配置config.name 和 config.age
     * @return
     */
    @GetMapping("/getConfig")
    public CommonResultCode getConfig() {
        return new CommonResultCode<>(200, "name: " + name, "age: " + age);
    }
}
