package com.inference.nubyx.integration.config;

import com.inference.nubyx.integration.interceptor.BearerAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;

public class ZendeskLeadClientConfig {

    @Bean
    public BearerAuthRequestInterceptor bearerAuthRequestInterceptor(){
        return new BearerAuthRequestInterceptor();
    }
}
