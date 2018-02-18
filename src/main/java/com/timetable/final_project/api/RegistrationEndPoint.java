package com.timetable.final_project.api;

import com.timetable.final_project.exceptions.EmailExistsException;
import com.timetable.final_project.exceptions.UsernameExistsException;
import com.timetable.final_project.controller.AccountService;
import com.timetable.final_project.controller.EmployeeService;
import com.timetable.final_project.helper_classes.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationEndPoint {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    AccountService accountService;

    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public UserRegistration registerUser(@RequestBody UserRegistration userRegistration) {
        try {
            accountService.registerAccount(userRegistration);
            userRegistration.setStatusCode(0);
            userRegistration.setMessage("success");
            return userRegistration;
            //return ResponseEntity.status(HttpStatus.OK).body(userRegistration);
        } catch (EmailExistsException e) {
            userRegistration.setStatusCode(2);
            userRegistration.setMessage("email in use");
            return userRegistration;
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userRegistration);
        } catch (UsernameExistsException e) {
            userRegistration.setStatusCode(1);
            userRegistration.setMessage("username in use");
            return userRegistration;
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userRegistration);
        }

    }
}

