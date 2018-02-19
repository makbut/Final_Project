package com.timetable.final_project.controller;

import com.timetable.final_project.exceptions.EmailExistsException;
import com.timetable.final_project.exceptions.NoSuchAccountException;
import com.timetable.final_project.exceptions.PasswordsNotMatchException;
import com.timetable.final_project.exceptions.UsernameExistsException;
import com.timetable.final_project.domain.Account;
import com.timetable.final_project.domain.Employee;
import com.timetable.final_project.helper_classes.ChangePassword;
import com.timetable.final_project.helper_classes.LoginInfo;
import com.timetable.final_project.helper_classes.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.rmi.runtime.Log;

@Service
@Transactional
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public UserRegistration registerAccount(UserRegistration userRegistration) throws EmailExistsException, UsernameExistsException{

        Employee employee = employeeRepository.findOneByEmailAddress(userRegistration.getEmailAddress());
        if(employee != null){
            throw new EmailExistsException();
        }else{
            Account account = accountRepository.findOneByUsername(userRegistration.getUsername());
            if(account != null){
                throw new UsernameExistsException();
            }else{
                employee = new Employee(
                        userRegistration.getFirstName(),
                        userRegistration.getLastName(),
                        userRegistration.getEmailAddress(),
                        userRegistration.getRole(),
                        userRegistration.getHourlyWage(),
                        userRegistration.getDaysOff()
                );
                account = new  Account(userRegistration.getUsername(), userRegistration.getPassword(), employeeRepository.save(employee));
                accountRepository.save(account);
                userRegistration.setPassword("****");
                return userRegistration;
            }
        }
    }


    public LoginInfo loginAccount(String username, String password) throws NoSuchAccountException {

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

    public LoginInfo changeUserPassword(ChangePassword changePassword) throws PasswordsNotMatchException, NoSuchAccountException {

        LoginInfo loginInfo;

        loginInfo = loginAccount(changePassword.getUsername(),changePassword.getCurrentPassword());

        if(!changePassword.getNewPassword().equals(changePassword.getConfirmNewPassword())){
            throw new PasswordsNotMatchException();
        }
        Account account = accountRepository.findOneByUsername(changePassword.getUsername());
        account.setPassword(changePassword.getNewPassword());
        return loginInfo;
    }


}
