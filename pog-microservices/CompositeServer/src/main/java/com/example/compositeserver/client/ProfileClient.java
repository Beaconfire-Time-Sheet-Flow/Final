package com.example.compositeserver.client;

import com.example.compositeserver.config.FeignHeadConfiguration;
import com.example.compositeserver.domain.ProfileDomain;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "employee-service")
public interface ProfileClient {
    @GetMapping("employee-service/getEmployee")
    ProfileDomain getEmployeeByUserId(@RequestParam Integer id);

    @PutMapping("employee-service/updateEmployee")
    String updateEmployeeById(@RequestBody ProfileDomain profileDomain);
}
