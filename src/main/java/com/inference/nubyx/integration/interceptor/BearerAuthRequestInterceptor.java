package com.inference.nubyx.integration.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;

public class BearerAuthRequestInterceptor implements RequestInterceptor {

    @Value("${client.zendesk.token}")
    private String token;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Authorization", "Bearer " + token);
    }
}
