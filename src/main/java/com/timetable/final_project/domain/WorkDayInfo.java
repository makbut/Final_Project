package com.timetable.final_project.domain;

import com.timetable.final_project.enums.Activity;
import com.timetable.final_project.enums.Workplace;

import javax.persistence.*;

@Entity
public class WorkDayInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int hours;
    private String date;
    private Activity activity;
    private Workplace workplace;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RELATED_EMPLOYEE")
    private Employee employee;

    public WorkDayInfo(){}

    public WorkDayInfo(Employee employee, String date, Workplace workplace, Activity activity, int hours){
        setEmployee(employee);
        setHours(hours);
        setDate(date);
        setActivity(activity);
        setWorkplace(workplace);
    }

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

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Workplace getWorkplace() {
        return workplace;
    }

    public void setWorkplace(Workplace workplace) {
        this.workplace = workplace;
    }
}

