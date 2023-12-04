package com.example.service1.configuration;


import brave.Tracer;
import io.micrometer.tracing.brave.bridge.BraveTracer;
import org.springframework.boot.actuate.autoconfigure.tracing.ConditionalOnEnabledTracing;
import org.springframework.boot.actuate.autoconfigure.tracing.MicrometerTracingAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.tracing.TracingProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@AutoConfiguration(before = MicrometerTracingAutoConfiguration.class)
@ConditionalOnClass({Tracer.class, BraveTracer.class})
@EnableConfigurationProperties(TracingProperties.class)
@ConditionalOnEnabledTracing
public class BraveAutoConfiguration {

}
