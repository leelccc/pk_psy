package com.lc.demostarterspringbootstarter.conf;

import com.lc.demostarterspringbootstarter.Digest;
import com.lc.demostarterspringbootstarter.impl.MD5Digest;
import com.lc.demostarterspringbootstarter.impl.ShaDigest;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(Setting.class)
public class Config {
    @Bean
    @ConditionalOnProperty(prefix = "digest",name = "type",havingValue = "md5")
    public Digest MD5Digest(){
        System.out.println( "加载MD5Digest");
        return new MD5Digest();
    }
    @Bean
    @ConditionalOnProperty(prefix = "digest",name = "type",havingValue = "sha")
    public Digest ShaDigest(){
        System.out.println( "加载ShaDigest");
        return new ShaDigest();
    }
}
