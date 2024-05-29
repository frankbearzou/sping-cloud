package org.cloud.commons.feign;

import java.util.List;

import org.cloud.commons.dto.PayDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("cloud-payment-service")
public interface PayFeignApi {

    @GetMapping("/pay/{id}")
    PayDto getPay(@PathVariable("id") int id);

    @GetMapping("/pay")
    List<PayDto> getPayList();
}
