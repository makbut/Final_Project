package com.timetable.final_project.api;

import com.timetable.final_project.exceptions.EmailNotExistsException;
import com.timetable.final_project.exceptions.UsernameNotExistsException;
import com.timetable.final_project.controller.AccountService;
import com.timetable.final_project.controller.EmployeeService;
import com.timetable.final_project.helper_classes.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationEndPoint {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    AccountService accountService;

    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public UserRegistration submitWorkDayInfo(@RequestBody UserRegistration userRegistration) {
        try {
            employeeService.getEmployeeByMail(userRegistration.getEmailAddress());
            userRegistration.setStatusCode(2);
            userRegistration.setMessage("email in use");
            return userRegistration;
        } catch (EmailNotExistsException e) {
            try {
                accountService.retrieveAccountByUsername(userRegistration.getUsername());
                userRegistration.setStatusCode(1);
                userRegistration.setMessage("username in use");
                return userRegistration;
            } catch (UsernameNotExistsException e1) {
                accountService.registerAccount(userRegistration, employeeService.registerEmployee(userRegistration));
                userRegistration.setStatusCode(0);
                userRegistration.setMessage("Success");
                return userRegistration;
            }
        }

    }
}

