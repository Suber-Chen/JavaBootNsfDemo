package com.suber.provider.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * @author suber
 * 2023/7/29 12:51
 */

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Component
public class RedisClient {
    @Value("${spring.redis.cluster.hosts}")
    static String hosts;
    // 设置最大连接数
    public JedisCluster getConnect() {
        String[] sockets = hosts.split(",");
        Set<HostAndPort> redisHosts = new HashSet<>();
        for (String socket : sockets
        ) {
            String[] hostAndPort = socket.split(":");
            redisHosts.add(new HostAndPort(hostAndPort[0], Integer.parseInt(hostAndPort[1])));
        }
        log.info("开始连接redis...");
        JedisCluster jedisCluster = new JedisCluster(redisHosts, 10000, 10000);
        log.info("连接redis成功...");
        return jedisCluster;
    }
}


