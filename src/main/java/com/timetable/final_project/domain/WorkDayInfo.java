package com.timetable.final_project.domain;

import javax.persistence.*;

@Entity
public class WorkDayInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int hours;
    private String activity;
    private String workplace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    Employee owner;

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


}

