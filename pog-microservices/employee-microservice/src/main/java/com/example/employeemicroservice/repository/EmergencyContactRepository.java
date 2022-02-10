package com.example.employeemicroservice.repository;

import com.example.employeemicroservice.entity.Contact;
import com.example.employeemicroservice.entity.EmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmergencyContactRepository  extends JpaRepository<EmergencyContact, Integer> {
    Optional<EmergencyContact> findEmergencyContactById(Integer id);
}
