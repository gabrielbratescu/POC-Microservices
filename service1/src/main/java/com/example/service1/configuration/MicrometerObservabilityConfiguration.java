package com.example.service1.configuration;


import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.observation.ServerRequestObservationContext;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

@Configuration(proxyBeanMethods = false)
public class MicrometerObservabilityConfiguration {

    @Bean
    ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
        return new ObservedAspect(observationRegistry);
    }

    @Bean
    ObservationRegistryCustomizer<ObservationRegistry> skipActuatorEndpointsFromObservation() {
        PathMatcher pathMatcher = new AntPathMatcher("/");
        return (registry) -> registry.observationConfig().observationPredicate((name, context) -> {
            if (context instanceof ServerRequestObservationContext observationContext) {
                return !pathMatcher.match("/actuator/**", observationContext.getCarrier().getRequestURI());
            } else {
                return true;
            }
        });
    }

    @Bean
    ObservationRegistryCustomizer<ObservationRegistry> skipSecuritySpansFromObservation() {
        return (registry) -> registry.observationConfig().observationPredicate((name, context) ->
                !name.startsWith("spring.security"));
    }
}
