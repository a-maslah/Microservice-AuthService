package com.amine.billingservice.feign;

import com.amine.billingservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestService {

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable(name = "id") Long id);
}
