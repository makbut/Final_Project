package com.timetable.final_project.helper_classes;

import com.timetable.final_project.domain.Employee;

public class LoginInfo {
    private int statuCode=1;
    private Long id;
    private String firsName;
    private String lastName;
    private String emailAddress;
    private int daysOff;
    private int wage;
    private String role;

    public void copyLoginInfo(Employee employee){
        setId(employee.getId());
        setFirsName(employee.getFirstName());
        setLastName(employee.getLastName());
        setEmailAddress(employee.getEmailAddress());
        setDaysOff(employee.getDaysOff());
        setWage(employee.getHourlyWage());
        setRole(employee.getRole());
    }

    public int getStatuCode() {
        return statuCode;
    }

    public void setStatuCode(int statuCode) {
        this.statuCode = statuCode;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}