package com.suber.provider.controller;

import com.suber.common.entities.CommonResultCode;
import com.suber.provider.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author suber
 * 2023/7/28 23:12
 */
@RestController
@Slf4j
@RequestMapping("/provider/redis")
public class RedisController {
    @Autowired
    private RedisService redisService;
    @GetMapping("set")
    public CommonResultCode setK(String key,String value){
        redisService.setK(key,value);
        return new CommonResultCode<>(200,"set redis key value 成功");
    }
    @GetMapping("sethash")
    public CommonResultCode setHash(String key, @RequestBody HashMap hashMap){
        redisService.setHash(key,hashMap);
        return new CommonResultCode<>(200,"setHash redis key value 成功");
    }

    @GetMapping("lpush")
    public CommonResultCode lPush(String key, @RequestBody String... list){
        redisService.lPush(key,list);
        return new CommonResultCode<>(200,"sadd redis key value 成功");
    }

    @GetMapping("sadd")
    public CommonResultCode sAdd(String key, @RequestBody String... set){
        redisService.sAdd(key,set);
        return new CommonResultCode<>(200,"sadd redis key value 成功");
    }

    @GetMapping("zadd")
    public CommonResultCode zAdd(String key, double num, String value){
        redisService.zAdd(key, num,value);
        return new CommonResultCode<>(200,"zadd redis key value 成功");
    }

    @GetMapping("del")
    public CommonResultCode delK(String key){
        redisService.delK(key);
        return new CommonResultCode<>(200,"del redis key 成功");
    }

    @GetMapping("get")
    public CommonResultCode getK(String key){
        String k = redisService.getK(key);
        return new CommonResultCode<>(200,"get redis key 成功",k);
    }

    @GetMapping("gethash")
    public CommonResultCode getHash(String key){
        Map<String, String> stringStringMap = redisService.hGetAll(key);
        return new CommonResultCode<>(200,"getHash redis key 成功",stringStringMap);
    }
    @GetMapping("smembers")
    public CommonResultCode sMembers(String key){
        Set strings = redisService.sMembers(key);
        return new CommonResultCode<>(200,"sMembers redis key 成功",strings);
    }
    @GetMapping("zrange")
    public CommonResultCode getSet(String key, @RequestParam(value = "start",defaultValue = "0")long start,@RequestParam(value = "end",defaultValue = "-1")long end){
        List<String> strings = redisService.zRange(key, start, end);
        return new CommonResultCode<>(200,"zRange redis key 成功",strings);
    }

    @GetMapping("lrange")
    public CommonResultCode getList(String key, @RequestParam(value = "start",defaultValue = "0")long start,@RequestParam(value = "end",defaultValue = "-1")long end){
        List<String> strings = redisService.lRange(key, start, end);
        return new CommonResultCode<>(200,"lrange redis key 成功",strings);
    }
}
