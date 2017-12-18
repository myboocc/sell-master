/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.imooc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 分布式锁
 * @author hongcj
 * @version V1.0
 * @since 2017-08-24 11:02
 */
@Component
public class RedisLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static  final Logger log = LoggerFactory.getLogger(RedisLock.class);
    /**
     * 加锁
     * @param key
     * @param value  时间戳+过期时间
     * @return
     */
    public boolean lock(String key, String value){
        if(redisTemplate.opsForValue().setIfAbsent(key,value)){
            //此处运用了redis的SETNX命令特性
            //将key设置值为value，如果key不存在，这种情况下等同SET命令。 当key存在时，什么也不做。SETNX是”SET if Not eXists”的简写
            //返回值如下：SETNX命令 1 如果key被设置了，0 如果key没有被设置
            //redis中文网详细介绍地址 http://www.redis.cn/commands/setnx.html
            //如果redis返回1，说明可以设置，表示缓存里不存在该锁，加锁成功
            return true;
        }
        //以下为预防死锁的情况，出现死锁的情况有很多，如IO异常，程序报错等导致解锁失败
        //1.获取redis里的当前值
        String currentValue = redisTemplate.opsForValue().get(key);
        //2.判断当前值是否已过期，过期情况进行下面操作
        if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()){
            //3.获取设置前的值,加锁
            //这里是为了处理多线程并发时，key和value相同时，保证只有线程能获取到锁
            //此处运用了redis的GETSET命令特性
            //执行过程如下：自动将key对应到value并且返回原来key对应的value。
            //redis中文网详细介绍地址 http://www.redis.cn/commands/getset.html
            String oldValue = redisTemplate.opsForValue().getAndSet(key,value);
            if(!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)){
                return true;
            }
        }
        return false;
    }

    /**
     * 解锁
     * @param key
     * @param value
     * @return
     */
    public void unlock(String key,String value){
        try{
            String currentValue = redisTemplate.opsForValue().get(key);
            if(!StringUtils.isEmpty(currentValue) && value.equals(currentValue)){
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        }catch (Exception e){
            log.error("Redis分布式锁解锁失败,{}",e);
        }
    }
}
