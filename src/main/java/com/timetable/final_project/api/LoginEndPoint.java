package com.timetable.final_project.api;

import com.timetable.final_project.controller.AccountService;
import com.timetable.final_project.domain.Account;
import com.timetable.final_project.exceptions.InvalidComboException;
import com.timetable.final_project.helper_classes.LoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
class LoginEndPoint {
    @Autowired
    AccountService accountService;

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity performLogin(@RequestBody Account account){
        LoginInfo loginInfo = new LoginInfo();
        try {
            loginInfo = accountService.loginAccount(account.getUsername(),account.getPassword());
        } catch (InvalidComboException e) {
            loginInfo.setStatusCode(1);
            loginInfo.setMessage("Invalid username/password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginInfo);
        }
        loginInfo.setStatusCode(0);
        loginInfo.setMessage("Success");
        return ResponseEntity.status(HttpStatus.OK).body(loginInfo);
    }
}
