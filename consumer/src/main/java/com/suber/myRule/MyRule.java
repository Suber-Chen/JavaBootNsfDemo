package com.suber.myRule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author suber
 * 2023/5/5 23:49
 */
@Configuration
public class MyRule {
    /**
     * 定义负载规格为随机
     * @return
     */
    @Bean
    public IRule mySelfRule(){
        return new WeightedResponseTimeRule();
    }
}
