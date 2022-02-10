package com.example.employeemicroservice.repository;

import com.example.employeemicroservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findAccountById(Integer id);
}
