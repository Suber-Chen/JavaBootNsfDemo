package com.suber.provider.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author suber
 * 2023/7/28 22:39
 */
public interface RedisService {

    public void setK(String key, String value);

    public void setHash(String key, HashMap hashMap);

    public void sAdd(String key, String... set);

    public void zAdd(String key, double num, String set);

    public void lPush(String key, String... set);

    public void delK(String key);

    public String getK(String key);


    public Map<String, String> hGetAll(String hash);

    public Set sMembers(String key);

    public List<String> zRange(String set, long start, long end);

    public List<String> lRange(String key, long start, long end);
}
