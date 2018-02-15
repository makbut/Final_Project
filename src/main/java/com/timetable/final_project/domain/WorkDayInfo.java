package com.timetable.final_project.domain;

import com.timetable.final_project.helper_classes.SubmitHours;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class WorkDayInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int hours;
    private String date;
    private String activity;
    private String workplace;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RELATED_EMPLOYEE")
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public WorkDayInfo(){

    }

    public WorkDayInfo(Employee employee, String date, String workplace, String activity, int hours){
        setEmployee(employee);
        setHours(hours);
        setDate(date);
        setActivity(activity);
        setWorkplace(workplace);
    }
}

