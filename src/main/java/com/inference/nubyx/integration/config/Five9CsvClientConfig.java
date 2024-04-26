package com.inference.nubyx.integration.config;

import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Encoder;
import feign.form.FormEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
public class Five9CsvClientConfig {
    @Value("${client.five9.auth.username}")
    private String username;
    @Value("${client.five9.auth.password}")
    private String password;

    private final ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(){
        return new  BasicAuthRequestInterceptor(username, password);
    }

    @Bean
    public Encoder feignFormEncoder(){
        return new FormEncoder(new SpringEncoder(messageConverters));
    }
}
