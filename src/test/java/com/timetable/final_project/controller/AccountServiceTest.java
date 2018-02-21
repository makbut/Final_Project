package com.timetable.final_project.controller;

import com.timetable.final_project.domain.Account;
import com.timetable.final_project.domain.Employee;
import com.timetable.final_project.exceptions.EmailExistsException;
import com.timetable.final_project.exceptions.InvalidComboException;
import com.timetable.final_project.exceptions.UsernameExistsException;
import com.timetable.final_project.helper_classes.UserRegistration;
import org.hibernate.validator.constraints.Mod10Check;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    Employee mockedEmployee;

    @Mock
    Account mockedAccount;

    @Mock
    UserRegistration mockedUserRegistration;

    @InjectMocks
    AccountService accountService;

    @Test
    public void registerAccountEmailExists() {

        UserRegistration userRegistration = new UserRegistration();
        userRegistration.setEmailAddress("makis0991@hotmail.com");

        when(employeeRepository.findOneByEmailAddress(any(String.class))).thenReturn(mockedEmployee);

        try {
            accountService.registerAccount(userRegistration);
            fail();
            return;
        } catch (EmailExistsException e) {
            return;
        } catch (UsernameExistsException e) {
            fail();
            return;
        }
    }

    @Test
    public void registerAccountUsernameExists() {

        UserRegistration userRegistration = new UserRegistration();
        userRegistration.setUsername("makis");

        when(employeeRepository.findOneByEmailAddress(any(String.class))).thenReturn(null);
        when(accountRepository.findOneByUsername(any(String.class))).thenReturn(mockedAccount);

        try {
            accountService.registerAccount(userRegistration);
            fail();
            return;
        } catch (EmailExistsException e) {
            fail();
            return;
        } catch (UsernameExistsException e) {
            return;
        }

    }

    @Test
    public void registerSuccess() {

        when(accountRepository.findOneByUsername(any(String.class))).thenReturn(null);
        when(employeeRepository.findOneByEmailAddress(any(String.class))).thenReturn(null);

        UserRegistration userRegistration1;
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.setPassword("1234");
        try {
            userRegistration1 = accountService.registerAccount(userRegistration);
        } catch (EmailExistsException e) {
            fail();
            return;
        } catch (UsernameExistsException e) {
            fail();
            return;
        }
        assertNotEquals("1234",userRegistration1.getPassword());
    }

    @Test
    public void loginFails() {
        when(accountRepository.findByUsernameAndPassword(any(String.class), any(String.class))).thenReturn(null);

        try {
            accountService.loginAccount(any(), any());
            fail();
            return;
        } catch (InvalidComboException e) {
            return;
        }
    }
}
