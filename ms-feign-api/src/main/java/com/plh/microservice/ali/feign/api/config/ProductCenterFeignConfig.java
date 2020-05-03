package com.plh.microservice.ali.feign.api.config;

import com.plh.microservice.ali.feign.api.handle.TulingRequestInterceptor;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * 这个类上千万不要添加@Configuration,不然会被作为全局配置文件共享
 *
 * @author 潘朗豪
 * @date 2020/4/25 23:25
 */
public class ProductCenterFeignConfig {

    @Bean
    public Logger.Level level() {
return Logger.Level.FULL;
//        return Logger.Level.BASIC;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new TulingRequestInterceptor();
    }
}
