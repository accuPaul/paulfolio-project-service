package com.paulmount.paulfolioprojectservice.config;

import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.time.Duration;

/**
 * *  Created by paulm on 1/29/2024
 */

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager createCacheWithinManager() {
        // creation of cache configuration
        CacheConfiguration<String, String> cacheConfiguration = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(
                        String.class, String.class,
                        ResourcePoolsBuilder.heap(1000).build()
                )
                .withExpiry(ExpiryPolicyBuilder
                        .timeToIdleExpiration(Duration.ofSeconds(3600))
                ).build();

        // fetching cacheManager
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();

        // parsing ehcache configuration to something SpringBoot will understand
        javax.cache.configuration.Configuration<String, String> configuration = Eh107Configuration.fromEhcacheCacheConfiguration(cacheConfiguration);

        // creating as many caches as you want
        cacheManager.createCache("projectCache", configuration);
        cacheManager.createCache("projectListCache", configuration);

        // add shutdown hook to close cacheManager
        Runtime.getRuntime().addShutdownHook(new Thread(cacheManager::close));

        // return Bean
        return cacheManager;
    }
}
