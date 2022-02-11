package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> checkLoginRepo(String username, String password){
        List<Account> res = accountRepository.getAccountsByUsername(username);
        if(res.size() == 0)
            return null;
        if(res.get(0).getPassword().equals(password))
            return res;
        else
            return null;
    }
}
