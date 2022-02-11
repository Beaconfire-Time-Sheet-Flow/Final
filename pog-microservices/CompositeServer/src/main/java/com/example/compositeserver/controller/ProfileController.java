package com.example.compositeserver.controller;

import com.example.compositeserver.domain.ProfileDomain;
import com.example.compositeserver.service.ProfileService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Api(tags = {"Employee - core service"})
public class ProfileController {
    private ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/getEmployee")
    public ResponseEntity<ProfileDomain> getEmployeeByUserId(@RequestParam Integer id) {
        return ResponseEntity.ok().body(profileService.getEmployeeByUserId(id));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/updateEmployee")
    public ResponseEntity<String> updateEmployeeById(@RequestBody ProfileDomain profileDomain){
        return ResponseEntity.ok().body(profileService.updateEmployeeById(profileDomain));
    }
}
