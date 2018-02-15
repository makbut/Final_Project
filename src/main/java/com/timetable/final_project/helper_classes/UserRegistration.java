package com.timetable.final_project.helper_classes;

public class UserRegistration {
    private int statusCode;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String role;
    private int daysOff;
    private int hourlyWage;
    private String username;
    private String password;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getDaysOff() {
        return daysOff;
    }

    public void setDaysOff(int daysOff) {
        this.daysOff = daysOff;
    }

    public int getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(int wage) {
        this.hourlyWage = wage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
