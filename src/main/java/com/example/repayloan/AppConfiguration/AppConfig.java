package com.example.repayloan.AppConfiguration;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

/**
 * @author samwel.wafula
 * Created on 16/03/2024
 * Time 09:01
 * Project RequestLoan
 */
@Getter
@Setter
@Configuration
public class AppConfig {

    @Value("${app.check_loan_balance_service_url_repay_loan}")
    private String repay_loan_to_check_balanceAndLimitService;

    @Bean
    WebClient webClient() {
        HttpClient httpClient = HttpClient.create().responseTimeout(Duration.ofMillis(5000));
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();

    }

//    @Bean
//    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
//        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
//                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build())
//                .circuitBreakerConfig(CircuitBreakerConfig.custom().failureRateThreshold(50).waitDurationInOpenState(Duration.ofMinutes(2)).build())
//                .build());
//
//    }
//
//
//    @Configuration
//    public class CircuitBreakerConfig {
//        @Bean
//        public CircuitBreaker circuitBreaker() {
//            CircuitBreakerConfig config = CircuitBreakerConfig.
//                    .failureRateThreshold(50) // Set the failure rate threshold
//                    .waitDurationInOpenState(Duration.ofMillis(1000)) // Time to wait in the open state before transitioning to half-open
//                    .build();
//            return CircuitBreaker.of("external-service", config);
//        }
//    }

}
