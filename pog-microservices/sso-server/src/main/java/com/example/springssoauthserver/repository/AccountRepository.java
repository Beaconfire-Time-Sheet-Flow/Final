package com.example.springssoauthserver.repository;

import com.example.springssoauthserver.entity.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    @Query("select a from Account a where a.username = :userName")
    List<Account> getAccountsByUsername(@Param("userName") String userName);
}
