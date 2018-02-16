package com.timetable.final_project.api;

import com.timetable.final_project.controller.AccountService;
import com.timetable.final_project.controller.EmployeeService;
import com.timetable.final_project.domain.Account;
import com.timetable.final_project.domain.Employee;
import com.timetable.final_project.helper_classes.SubmitHours;
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

        if (employeeService.getEmployeeByMail(userRegistration.getEmailAddress()) != null) {
            userRegistration.setStatusCode(1);
            return userRegistration;
        }
        if (accountService.retrieveAccountByUsername(userRegistration.getUsername()) != null) {
            userRegistration.setStatusCode(2);
            return userRegistration;
        }

        Employee employee = new Employee(
                userRegistration.getFirstName(),
                userRegistration.getLastName(),
                userRegistration.getEmailAddress(),
                userRegistration.getRole(),
                userRegistration.getHourlyWage(),
                userRegistration.getDaysOff()
        );
        Account account = new Account(userRegistration.getUsername(), userRegistration.getPassword(), employee);

        employeeService.saveEmployee(employee);
        accountService.saveAccount(account);
        userRegistration.setStatusCode(0);
        
        return userRegistration;
    }
}

