package com.suber.consumer.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author suber
 * 2023/5/6 1:31
 */
@Configuration
public class OpenFeignConfig {
    @Bean
    Logger.Level openFeignloggerLevel(){
        return Logger.Level.FULL;
    }
}
