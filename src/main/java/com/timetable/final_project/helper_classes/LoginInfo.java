package com.timetable.final_project.helper_classes;

import com.timetable.final_project.domain.Employee;
import com.timetable.final_project.enums.Role;
import com.timetable.final_project.enums.Workplace;

public class LoginInfo {
    private int statusCode;
    private String message;
    private Long id;
    private String firsName;
    private String lastName;
    private String emailAddress;
    private int daysOff;
    private int wage;
    private Role role;
    private Workplace lastWorkplace;

    public void copyLoginInfo(Employee employee){
        setId(employee.getId());
        setFirsName(employee.getFirstName());
        setLastName(employee.getLastName());
        setEmailAddress(employee.getEmailAddress());
        setDaysOff(employee.getDaysOff());
        setWage(employee.getHourlyWage());
        setRole(employee.getRole());
        setLastWorkplace(employee.getLastWorkplace());
    }

    public Workplace getLastWorkplace() {
        return lastWorkplace;
    }

    public void setLastWorkplace(Workplace lastWorkplace) {
        this.lastWorkplace = lastWorkplace;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getDaysOff() {
        return daysOff;
    }

    public void setDaysOff(int daysOff) {
        this.daysOff = daysOff;
    }

    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
