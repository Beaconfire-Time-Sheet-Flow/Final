package com.example.employeemicroservice.controller;

import com.example.employeemicroservice.domain.ContactDomain;
import com.example.employeemicroservice.domain.EmergencyContactDomain;
import com.example.employeemicroservice.domain.ProfileDomain;
import com.example.employeemicroservice.entity.Account;
import com.example.employeemicroservice.entity.Contact;
import com.example.employeemicroservice.entity.EmergencyContact;
import com.example.employeemicroservice.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/profile")
public class profileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/getEmployee")
            //@RequestHeader("userName") String userName
    public ResponseEntity<ProfileDomain> getEmployeeByUserId(@RequestParam Integer id) {
        ProfileDomain profileDomain = new ProfileDomain();
        Contact contact = profileService.getContactById(id).get();
        ContactDomain contactDomain = ContactDomain.builder()
                .id(contact.getId())
                .cellPhone(contact.getCellPhone())
                .email(contact.getEmail())
                .addressLine1(contact.getAddressLine1())
                .addressLine2(contact.getAddressLine2())
                .city(contact.getCity())
                .zipCode(contact.getZipCode())
                .state(contact.getState())
                .build();
        profileDomain.setContactDomain(contactDomain);

        EmergencyContact emergencyContact = profileService.getEmergencyContactById(id).get();
        EmergencyContactDomain emergencyContactDomain = EmergencyContactDomain.builder()
                .id(emergencyContact.getId())
                .firstName(emergencyContact.getFirstName())
                .lastName(emergencyContact.getLastName())
                .cellPhone(emergencyContact.getCellPhone())
                .build();

        profileDomain.setEmergencyContactDomain(emergencyContactDomain);
        return ResponseEntity.ok().body(profileDomain);
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<String> updateEmployeeById(@RequestBody ProfileDomain profileDomain){
        ContactDomain contactDomain = profileDomain.getContactDomain();
        System.out.println(contactDomain);
        EmergencyContactDomain emergencyContactDomain = profileDomain.getEmergencyContactDomain();
        System.out.println(emergencyContactDomain);
        profileService.updateContact(contactDomain);

        profileService.updateEmergencyContact(emergencyContactDomain);


        return ResponseEntity.ok().build();
    }
}
