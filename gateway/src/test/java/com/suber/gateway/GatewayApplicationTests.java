package com.suber.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class GatewayApplicationTests {

    @Test
    void contextLoads() {
        String passwd = "nacos";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(passwd);
        System.out.println(encode);

        System.out.println(bCryptPasswordEncoder.matches(passwd,"$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu"));
    }

}
