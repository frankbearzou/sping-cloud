package org.cloud.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"org.cloud.commons.feign"})
public class FeignConfig {

}
