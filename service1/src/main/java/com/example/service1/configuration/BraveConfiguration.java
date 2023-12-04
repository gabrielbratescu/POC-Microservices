package com.example.service1.configuration;


import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin2.Span;
import zipkin2.codec.SpanBytesEncoder;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Reporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.brave.ZipkinSpanHandler;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(ZipkinSpanHandler.class)
public class BraveConfiguration {


    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(Reporter.class)
    public ZipkinSpanHandler zipkinSpanHandler(Sender sender) {

        AsyncReporter<Span> reporter = AsyncReporter.builder(sender)
                .build(SpanBytesEncoder.JSON_V1);

        return (ZipkinSpanHandler) ZipkinSpanHandler.newBuilder(reporter).build();
    }

}
