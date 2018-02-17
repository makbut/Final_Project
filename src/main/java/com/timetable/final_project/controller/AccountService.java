package com.timetable.final_project.controller;

import com.timetable.final_project.exceptions.NoSuchAccountException;
import com.timetable.final_project.exceptions.UsernameNotExistsException;
import com.timetable.final_project.domain.Account;
import com.timetable.final_project.domain.Employee;
import com.timetable.final_project.helper_classes.LoginInfo;
import com.timetable.final_project.helper_classes.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public Account registerAccount(UserRegistration userRegistration, Employee employee){
        Account account = new Account(userRegistration.getUsername(), userRegistration.getPassword(), employee);
        return accountRepository.save(account);
    }

    public Account retrieveAccountByUsername(String username) throws UsernameNotExistsException{
        Account account = accountRepository.findOneByUsername(username);
        if(account == null){
            throw new UsernameNotExistsException();
        }
        return account;
    }

    public LoginInfo retrieveAccount(String username, String password) throws NoSuchAccountException {

        Account account = accountRepository.findByUsernameAndPassword(username, password);

        LoginInfo loginInfo = new LoginInfo();
        Employee employee;
        if(account == null) {
            throw new NoSuchAccountException();
        }
        employee = employeeRepository.findOne(account.getEmployee().getId());
        loginInfo.copyLoginInfo(employee);
        return loginInfo;
    }
}
