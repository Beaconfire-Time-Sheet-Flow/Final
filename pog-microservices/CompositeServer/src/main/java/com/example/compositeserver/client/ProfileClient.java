package com.example.compositeserver.client;

import com.example.compositeserver.config.FeignHeadConfiguration;
import com.example.compositeserver.domain.ProfileDomain;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "employee-core-service", path = "/", configuration = FeignHeadConfiguration.class)
public interface ProfileClient {
    @GetMapping("/getEmployee")
    ProfileDomain getEmployeeByUserId(@RequestParam Integer id);

    @PutMapping("/updateEmployee")
    String updateEmployeeById(@RequestBody ProfileDomain profileDomain);
}
