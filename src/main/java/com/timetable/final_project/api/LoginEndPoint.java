package com.timetable.final_project.api;

import com.timetable.final_project.controller.AccountService;
import com.timetable.final_project.controller.EmployeeService;
import com.timetable.final_project.domain.Account;
import com.timetable.final_project.domain.Employee;
import com.timetable.final_project.exceptions.NoSuchAccountException;
import com.timetable.final_project.helper_classes.LoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;

@RestController
class LoginEndPoint {
    @Autowired
    AccountService accountService;

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginInfo performLogin(@RequestBody Account account){
        LoginInfo loginInfo = new LoginInfo();
        try {
            loginInfo = accountService.retrieveAccount(account.getUsername(),account.getPassword());
            loginInfo.setStatuCode(0);
            loginInfo.setMessage("Success!!");
            return loginInfo;
        } catch (NoSuchAccountException e) {
            loginInfo.setStatuCode(1);
            loginInfo.setMessage("Invalid username/password");
            return loginInfo;
        }
    }
}
