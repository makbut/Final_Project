package com.timetable.final_project.api;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.timetable.final_project.controller.WorkDayInfoService;
import com.timetable.final_project.domain.WorkDayInfo;
import com.timetable.final_project.exceptions.InvalidDatesException;
import com.timetable.final_project.exceptions.NoSuchEmployeeException;
import com.timetable.final_project.helper_classes.DateString;
import com.timetable.final_project.helper_classes.SubmitHours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class OverviewHoursEndPoint {

    @Autowired
    WorkDayInfoService workDayInfoService;

    @CrossOrigin
    @RequestMapping(value = "/employee/{id}/overviewHours", method = RequestMethod.GET)
    public ResponseEntity overviewHours(@PathVariable("id") long id) {

        List<WorkDayInfo> workDayInfoList = null;
        try {
            List<SubmitHours> submitHoursList = new ArrayList<>();
            workDayInfoList = workDayInfoService.getWorkDayInfoGivenID(id);
            workDayInfoList.forEach(workDayInfo -> {
                SubmitHours submitHours = new SubmitHours(workDayInfo, 0, "Success");
                submitHoursList.add(submitHours);
            });
            return ResponseEntity.status(HttpStatus.OK).body(submitHoursList);
        } catch (NoSuchEmployeeException e) {
            SubmitHours submitHours = new SubmitHours(1, "Invalid employee id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(submitHours);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/employee/{id}/overviewHours/{finalized}", method = RequestMethod.GET)
    public ResponseEntity overviewHoursByFinalization(@PathVariable("id") long id, @PathVariable("finalized") boolean finalized) {

        List<WorkDayInfo> workDayInfoList = null;
        try {
            List<SubmitHours> submitHoursList = new ArrayList<>();
            workDayInfoList = workDayInfoService.getWorkDayInfoGivenIDandFinal(id,finalized);
            workDayInfoList.forEach(workDayInfo -> {
                SubmitHours submitHours = new SubmitHours(workDayInfo, 0, "Success");
                submitHoursList.add(submitHours);
            });
            return ResponseEntity.status(HttpStatus.OK).body(submitHoursList);
        } catch (NoSuchEmployeeException e) {
            SubmitHours submitHours = new SubmitHours(1, "Invalid employee id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(submitHours);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/employee/{id}/overviewHours/{startDate}/{endDate}", method = RequestMethod.GET)
    public ResponseEntity overviewHoursByDate(@PathVariable("id") long id, @PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) {
        Iterable<SubmitHours> submitHoursList = null;
        try {
            submitHoursList = workDayInfoService.getWorkDayInfoGivenDatesAndID(id,startDate,endDate);
            return ResponseEntity.status(HttpStatus.OK).body(submitHoursList);
        } catch (NoSuchEmployeeException e) {
            SubmitHours submitHours = new SubmitHours(1, "Invalid employee id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(submitHours);
        } catch (InvalidDatesException e) {
            SubmitHours submitHours = new SubmitHours(2, "Invalid input dates");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(submitHours);
        }
    }
}

/*
* TODO
* Check if not long id is not provided
* Send enums with @GET
* Create overview by workplace (and activity)
* */
