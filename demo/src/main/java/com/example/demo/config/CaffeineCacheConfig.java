package com.example.demo.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
@Slf4j
public class CaffeineCacheConfig {
    /**
     * CacheManager实现
     * @return
     */
    @Bean("cacheManager")
    public CacheManager cacheManager(){
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        //集合缓存
        ArrayList<CaffeineCache> caches= new ArrayList<>();
        caches.add(new CaffeineCache("users-cache",
                Caffeine.newBuilder()
                        //指定Key下的最大缓存数据量
        .maximumSize(1000)
                        //最后一次访问之后120秒过期
                        .expireAfterAccess(120, TimeUnit.SECONDS)
        .build()));
        cacheManager.setCaches(caches);
        return cacheManager;
    }
}
