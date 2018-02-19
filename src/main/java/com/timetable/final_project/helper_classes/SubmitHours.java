package com.timetable.final_project.helper_classes;

import com.timetable.final_project.domain.WorkDayInfo;
import com.timetable.final_project.enums.Activity;
import com.timetable.final_project.enums.Workplace;

public class SubmitHours {
    private int statusCode;
    private String message;
    private Long employeeId;
    private int hours;
    private String date;
    private Activity activity;
    private Workplace workplace;

    // Success
    public SubmitHours(WorkDayInfo workDayInfo, int code, String message) {
        setMessage(message);
        setStatusCode(code);
        setEmployeeId(workDayInfo.getEmployee().getId());
        setHours(workDayInfo.getHours());
        setDate(workDayInfo.getDate());
        setActivity(workDayInfo.getActivity());
        setWorkplace(workDayInfo.getWorkplace());
    }

    // Invalid
    public SubmitHours(int code, String message) {
        setStatusCode(code);
        setMessage(message);
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
