package com.example.compositeserver.service;

import com.example.compositeserver.client.ProfileClient;
import com.example.compositeserver.domain.ProfileDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private ProfileClient profileClient;

    @Autowired
    public ProfileService(ProfileClient profileClient) {
        this.profileClient = profileClient;
    }

    public ProfileDomain getEmployeeByUserId(Integer id) {
        return profileClient.getEmployeeByUserId(id);
    }

    public String updateEmployeeById(ProfileDomain profileDomain) {
        return profileClient.updateEmployeeById(profileDomain);
    }
}
