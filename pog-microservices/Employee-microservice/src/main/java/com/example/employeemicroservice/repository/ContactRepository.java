package com.example.employeemicroservice.repository;

import com.example.employeemicroservice.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    Optional<Contact> findContactById(Integer id);
}
