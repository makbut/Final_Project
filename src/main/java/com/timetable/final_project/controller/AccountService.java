package com.timetable.final_project.controller;

import com.timetable.final_project.domain.Account;
import com.timetable.final_project.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Account retrieveAccount(String username, String password) {
        return accountRepository.findByUsernameAndPassword(username, password);
    }

    public Account saveAccount(Account account){
        return accountRepository.save(account);
    }

    public Account retrieveAccountByUsername(String username){
        return accountRepository.findOneByUsername(username);
    }

}
