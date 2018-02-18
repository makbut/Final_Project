package com.timetable.final_project.api;


import com.timetable.final_project.controller.AccountService;
import com.timetable.final_project.exceptions.NoSuchAccountException;
import com.timetable.final_project.exceptions.PasswordsNotMatchException;
import com.timetable.final_project.helper_classes.ChangePassword;
import com.timetable.final_project.helper_classes.LoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChangePasswordEndPoint {

    @Autowired
    AccountService accountService;

    @CrossOrigin
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public LoginInfo changePassword(@RequestBody ChangePassword changePassword){
        LoginInfo loginInfo = new LoginInfo();
        try {
            loginInfo = accountService.retrieveAccount(changePassword.getUsername(),changePassword.getCurrentPassword());
            accountService.changeUserPassword(changePassword.getUsername(),changePassword.getNewPassword(),changePassword.getConfirmNewPassword());
            loginInfo.setStatuCode(0);
            loginInfo.setMessage("Success!!");
            return loginInfo;
        } catch (NoSuchAccountException e) {
            loginInfo.setStatuCode(1);
            loginInfo.setMessage("Invalid combination username/password!!");
            return loginInfo;
        } catch (PasswordsNotMatchException e) {
            loginInfo.setStatuCode(2);
            loginInfo.setMessage("Invalid combination new passwords");
            return loginInfo;
        }
    }
}
