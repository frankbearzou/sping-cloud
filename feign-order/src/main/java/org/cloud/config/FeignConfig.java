package org.cloud.config;

import feign.Retryer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"org.cloud.commons.feign"})
public class FeignConfig {

    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(100, 5000, 3);
    }
}
