package com.example.service1.configuration;


import brave.Tracing;
import brave.http.HttpTracing;
import brave.spring.web.TracingClientHttpRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    RestTemplate restTemplate(HttpTracing httpTracing) {

        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(new HttpComponentsClientHttpRequestFactory()));
        restTemplate.getInterceptors().add(new HttpLoggingInterceptor());
        restTemplate.getInterceptors().add(TracingClientHttpRequestInterceptor.create(httpTracing));
        return restTemplate;
    }

    @Bean
    public HttpTracing create(Tracing tracing) {
        return HttpTracing
                .newBuilder(tracing)
                .build();
    }

}
