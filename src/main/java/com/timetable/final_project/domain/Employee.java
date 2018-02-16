package com.timetable.final_project.domain;

import com.timetable.final_project.helper_classes.UserRegistration;

import javax.persistence.*;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String role;
    private int daysOff;
    private int hourlyWage;

    @OneToMany(mappedBy="employee")
    private List<WorkDayInfo> workDays;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<WorkDayInfo> getWorkDays() {
        return workDays;
    }

    public void setWorkDays(List<WorkDayInfo> days) {
        this.workDays = days;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getDaysOff() {
        return daysOff;
    }

    public void setDaysOff(int daysOff) {
        this.daysOff = daysOff;
    }

    public int getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(int hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public Employee(){

    }

    public Employee(String firstName, String lastName, String emailAddress, String role, int hourlyWage, int daysOff){
        setFirstName(firstName);
        setLastName(lastName);
        setEmailAddress(emailAddress);
        setRole(role);
        setHourlyWage(hourlyWage);
        setDaysOff(daysOff);
    }
}
