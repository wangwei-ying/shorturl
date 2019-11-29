package com.github.wwying.config;


import com.github.wwying.service.ShortUrlService;
import com.github.wwying.service.TokenCreator;
import com.github.wwying.service.impl.BinaryTokenCreator;
import com.github.wwying.service.impl.ShortUrlServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    @ConditionalOnMissingBean
    public TokenCreator tokenCreator(){
        return new BinaryTokenCreator();
    }


    @Bean
    @ConditionalOnMissingBean
    public ShortUrlService shortUrlService(TokenCreator tokenCreator){
        return new ShortUrlServiceImpl(tokenCreator);
    }




}
