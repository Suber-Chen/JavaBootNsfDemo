package com.suber.provider.service.impl;

import com.suber.provider.service.RedisService;
import com.suber.provider.util.RedisClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author suber
 * 2023/7/28 22:43
 */
@Service
@Log4j2
public class RedisServiceImpl implements RedisService {
    @Autowired
    RedisClient client;

    @Override
    public void setK(String key, String value) {
        log.info("开始执行 :  set {}  {} ", key, value);
        client.getConnect().setnx(key, value);
        log.info("成功执行 : set {}  {} ", key, value);
    }

    @Override
    public void setHash(String key, HashMap hashMap) {
        log.info("开始执行 :  hset {}  {} ", key, hashMap);
        client.getConnect().hset(key, hashMap);
        log.info("成功执行 :  hset {}  {} ", key, hashMap);
    }

    @Override
    public void sAdd(String key, String... set) {
        log.info("开始执行 :  sadd {}  {} ", key, set);
        client.getConnect().sadd(key, set);
        log.info("成功执行 :  sadd {}  {} ", key, set);
    }

    @Override
    public void zAdd(String key, double num, String set) {
        log.info("开始执行 :  sadd {}  {} ", key, set);
        client.getConnect().zadd(key, num, set);
        log.info("成功执行 :  sadd {}  {} ", key, set);
    }

    @Override
    public void lPush(String key, String... list) {
        log.info("开始执行 :  lpush {}  {} ", key, list);
        client.getConnect().lpush(key, list);
        log.info("成功执行 :  lpush {}  {} ", key, list);
    }

    @Override
    public void delK(String key) {
        log.info("开始执行 : del {}", key);
        client.getConnect().del(key);
        log.info("成功执行 : del {}", key);
    }

    @Override
    public String getK(String key) {
        log.info("开始执行 : get {}", key);
        String s = client.getConnect().get(key);
        if (s.equals("")) {
            log.error("key {} 不存在", key);
            return "key " + key + "不存在";
        } else {
            log.info("成功执行 : get {}", key);
            return s;
        }
    }

    @Override
    public Map<String, String> hGetAll(String hash) {
        log.info("开始执行 : hGetAll {}", hash);
        Map<String, String> stringStringMap = client.getConnect().hgetAll(hash);
        if (stringStringMap == null) {
            log.error("key {} 不存在", hash);
            return null;
        } else {
            log.info("成功执行 : hGetAll {}", hash);
            return stringStringMap;
        }
    }

    @Override
    public Set sMembers(String key) {
        log.info("开始执行 : sMembers {}", key);
        Set<String> smembers = client.getConnect().smembers(key);
        if (smembers == null) {
            log.error("key {} 不存在", key);
            return null;
        } else {
            log.info("成功执行 : sMembers {}", key);
            return smembers;
        }
    }

    @Override
    public List<String> zRange(String set, long start, long end) {
        log.info("开始执行 : zRange {} {} {}", set, start, end);
        List<String> zrange = client.getConnect().zrange(set, start, end);
        if (zrange == null) {
            log.error("key {} 不存在", set);
            return null;
        }
        log.info("成功执行 : zRange {} {} {}", set, start, end);
        return zrange;
    }

    @Override
    public List<String> lRange(String key, long start, long end) {
        log.info("开始执行 : lrange {} {} {}", key, start, end);
        List<String> lrange = client.getConnect().lrange(key, start, end);
        if (lrange == null) {
            log.error("key {} 不存在", key);
            return null;
        }
        log.info("成功执行 : lrange {} {} {}", key, start, end);
        return lrange;
    }
}
