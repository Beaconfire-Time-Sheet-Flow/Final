package com.example.springssoauthserver.service;


//import com.example.springssoauthserver.dao.AccountDAO;
import com.example.springssoauthserver.entity.Account;
import com.example.springssoauthserver.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
