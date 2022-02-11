package com.example.demo.repository;

import com.example.demo.entity.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    @Query("select a from Account a where a.username=:username")
    List<Account> getAccountsByUsername(@Param("username") String username);
}
