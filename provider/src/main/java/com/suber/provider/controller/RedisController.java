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

    /**
     * 增 string 类型
     * @param key
     * @param value
     * @return
     */
    @PostMapping("set")
    public CommonResultCode setK(String key,String value){
        redisService.setK(key,value);
        return new CommonResultCode<>(200,"set redis key value 成功");
    }

    /**
     * 增 hash map 类型
     * @param key
     * @param hashMap
     * @return
     */
    @PostMapping("sethash")
    public CommonResultCode setHash(String key, @RequestBody HashMap hashMap){
        redisService.setHash(key,hashMap);
        return new CommonResultCode<>(200,"setHash redis key value 成功");
    }


    /**
     * 增 list 类型
     * @param key
     * @param list
     * @return
     */
    @PostMapping("lpush")
    public CommonResultCode lPush(String key, @RequestBody String... list){
        redisService.lPush(key,list);
        return new CommonResultCode<>(200,"lpush redis key value 成功");
    }

    /**
     * 增 Set 类型
     * @param key
     * @param set
     * @return
     */
    @PostMapping("sadd")
    public CommonResultCode sAdd(String key, @RequestBody String... set){
        redisService.sAdd(key,set);
        return new CommonResultCode<>(200,"sadd redis key value 成功");
    }

    /**
     * 增 有序集合 sorted set 类型
     * @param key
     * @param num
     * @param value
     * @return
     */
    @PostMapping("zadd")
    public CommonResultCode zAdd(String key, double num, String value){
        redisService.zAdd(key, num,value);
        return new CommonResultCode<>(200,"zadd redis key value 成功");
    }

    /**
     * 删 所有 类型， 传入key即可
     * @param key
     * @return
     */
    @DeleteMapping("del")
    public CommonResultCode delK(String key){
        redisService.delK(key);
        return new CommonResultCode<>(200,"del redis key 成功");
    }

    /**
     * 查 string 类型
     * @param key
     * @return
     */
    @GetMapping("get")
    public CommonResultCode getK(String key){
        String k = redisService.getK(key);
        return new CommonResultCode<>(200,"get redis key 成功",k);
    }

    /**
     * 查 hash map 类型
     * @param key
     * @return
     */
    @GetMapping("gethash")
    public CommonResultCode getHash(String key){
        Map<String, String> stringStringMap = redisService.hGetAll(key);
        return new CommonResultCode<>(200,"getHash redis key 成功",stringStringMap);
    }

    /**
     * 查 set 类型中的全部
     * @param key
     * @return
     */
    @GetMapping("smembers")
    public CommonResultCode sMembers(String key){
        Set strings = redisService.sMembers(key);
        return new CommonResultCode<>(200,"sMembers redis key 成功",strings);
    }

    /**
     * 查 sorted set 类型 可以指定范围
     * @param key
     * @param start
     * @param end
     * @return
     */
    @GetMapping("zrange")
    public CommonResultCode getSet(String key, @RequestParam(value = "start",defaultValue = "0")long start,@RequestParam(value = "end",defaultValue = "-1")long end){
        List<String> strings = redisService.zRange(key, start, end);
        return new CommonResultCode<>(200,"zRange redis key 成功",strings);
    }


    /**
     * 查 list 类型 可以指定范围
     * @param key
     * @param start
     * @param end
     * @return
     */
    @GetMapping("lrange")
    public CommonResultCode getList(String key, @RequestParam(value = "start",defaultValue = "0")long start,@RequestParam(value = "end",defaultValue = "-1")long end){
        List<String> strings = redisService.lRange(key, start, end);
        return new CommonResultCode<>(200,"lrange redis key 成功",strings);
    }
}
