package com.timetable.final_project.api;

import com.timetable.final_project.controller.AccountService;
import com.timetable.final_project.controller.EmployeeService;
import com.timetable.final_project.domain.Account;
import com.timetable.final_project.domain.Employee;
import com.timetable.final_project.helper_classes.LoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
class LoginEndPoint {
    @Autowired
    AccountService accountService;

    @Autowired
    EmployeeService employeeService;

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginInfo performLogin(@RequestBody Account account){
        Account account1 = accountService.retrieveAccount(account.getUsername(),account.getPassword());
        LoginInfo loginInfo = new LoginInfo();
        Employee employee;
        if(account1 != null) {
            employee = employeeService.getEmployeeById(account1.getEmployee().getId());
            loginInfo.copyLoginInfo(employee);
            loginInfo.setStatuCode(0);
            loginInfo.setMessage("Success!!");
        }
        return loginInfo;
    }

}
