package com.timetable.final_project.api;


import com.timetable.final_project.controller.AccountService;
import com.timetable.final_project.exceptions.InvalidComboException;
import com.timetable.final_project.exceptions.PasswordsNotMatchException;
import com.timetable.final_project.helper_classes.ChangePassword;
import com.timetable.final_project.helper_classes.LoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChangePasswordEndPoint {

    @Autowired
    AccountService accountService;

    @CrossOrigin
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public ResponseEntity changePassword(@RequestBody ChangePassword changePassword){
        LoginInfo loginInfo = new LoginInfo();
        try {
            loginInfo = accountService.changeUserPassword(changePassword);
        } catch (InvalidComboException e) {
            loginInfo.setStatusCode(1);
            loginInfo.setMessage("Invalid combination username/password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginInfo);
        } catch (PasswordsNotMatchException e) {
            loginInfo.setStatusCode(2);
            loginInfo.setMessage("Invalid combination new passwords");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginInfo);
        }
        loginInfo.setStatusCode(0);
        loginInfo.setMessage("Success");
        return ResponseEntity.status(HttpStatus.OK).body(loginInfo);
    }
}
