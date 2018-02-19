package com.timetable.final_project.helper_classes;

import com.timetable.final_project.domain.WorkDayInfo;
import com.timetable.final_project.enums.Activity;
import com.timetable.final_project.enums.Workplace;

import java.time.LocalDate;

public class SubmitHours {
    private int statusCode;
    private String message;
    private Long employeeId;
    private int hours;
    private String date;
    private Activity activity;
    private Workplace workplace;
    private Workplace lastWorkplace;
    private boolean finalized;

    //default
    public SubmitHours(){}

    // Success
    public SubmitHours(WorkDayInfo workDayInfo, int code, String message) {
        setMessage(message);
        setStatusCode(code);
        setEmployeeId(workDayInfo.getEmployee().getId());
        setHours(workDayInfo.getHours());
        setDate(workDayInfo.getDate().toString());
        setActivity(workDayInfo.getActivity());
        setWorkplace(workDayInfo.getWorkplace());
        setFinalized(workDayInfo.isFinalized());
    }

    // Invalid
    public SubmitHours(int code, String message) {
        setStatusCode(code);
        setMessage(message);
    }

    public boolean isFinalized() {
        return finalized;
    }

    public void setFinalized(boolean finalized) {
        this.finalized = finalized;
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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
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
