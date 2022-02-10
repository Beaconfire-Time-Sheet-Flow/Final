package com.example.employeemicroservice.service;

import com.example.employeemicroservice.domain.ContactDomain;
import com.example.employeemicroservice.domain.EmergencyContactDomain;
import com.example.employeemicroservice.domain.ProfileDomain;
import com.example.employeemicroservice.entity.Account;
import com.example.employeemicroservice.entity.Contact;
import com.example.employeemicroservice.entity.EmergencyContact;
import com.example.employeemicroservice.repository.AccountRepository;
import com.example.employeemicroservice.repository.ContactRepository;
import com.example.employeemicroservice.repository.EmergencyContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private EmergencyContactRepository emergencyContactRepository;

    @Transactional
    public Optional<Account> getEmployeeById(Integer id){
        return accountRepository.findAccountById(id);
    }

    @Transactional
    public Optional<Contact> getContactById(Integer id){
        return contactRepository.findContactById(id);
    }

    @Transactional
    public Optional<EmergencyContact> getEmergencyContactById(Integer id){
        return emergencyContactRepository.findEmergencyContactById(id);
    }

//    @Transactional
//    public ProfileDomain getProfileDomainById(Integer id){
//        Account employee = getEmployeeById(id).get();
//        ContactDomain contactDomain = new ContactDomain();
//
//    }

    @Transactional
    public Contact updateContact(ContactDomain contactDomain){
        Contact contact = getContactById(contactDomain.getId()).get();
        contact.setCellPhone(contactDomain.getCellPhone());
        contact.setEmail(contactDomain.getEmail());
        contact.setAddressLine1(contactDomain.getAddressLine1());
        contact.setAddressLine2(contactDomain.getAddressLine2());
        contact.setCity(contactDomain.getCity());
        contact.setState(contactDomain.getState());

        return (Contact)contactRepository.save(contact);
    }

    @Transactional
    public EmergencyContact updateEmergencyContact(EmergencyContactDomain emergencyContactDomain){
        EmergencyContact emergencyContact = getEmergencyContactById(emergencyContactDomain.getId()).get();
        emergencyContact.setFirstName(emergencyContactDomain.getFirstName());
        emergencyContact.setLastName(emergencyContactDomain.getLastName());
        emergencyContact.setCellPhone(emergencyContactDomain.getCellPhone());
        return (EmergencyContact) emergencyContactRepository.save(emergencyContact);
    }

}
