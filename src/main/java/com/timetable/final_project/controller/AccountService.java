package com.timetable.final_project.controller;

import com.timetable.final_project.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    boolean isValidUser (String username, String password) {
        Account account = accountRepository.findOneByUsernameAndPassword (username , password);
        if(account != null){
            return true;
        }else {
            return false;
        }
    }
}
