package com.timetable.final_project.domain;

import com.timetable.final_project.enums.Activity;
import com.timetable.final_project.enums.Workplace;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class WorkDayInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int hours;
    private LocalDate date;
    private Activity activity;
    private Workplace workplace;
    private boolean finalized;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RELATED_EMPLOYEE")
    private Employee employee;

    public WorkDayInfo(){}

    public void copyInfo(Employee employee, LocalDate date, Workplace workplace, Activity activity, int hours, boolean finalized){
        setEmployee(employee);
        setHours(hours);
        setDate(date);
        setActivity(activity);
        setWorkplace(workplace);
        setFinalized(finalized);
    }

    public boolean isFinalized() {
        return finalized;
    }

    public void setFinalized(boolean finalized) {
        this.finalized = finalized;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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

