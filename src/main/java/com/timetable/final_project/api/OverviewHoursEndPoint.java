package com.timetable.final_project.api;


import com.timetable.final_project.controller.WorkDayInfoService;
import com.timetable.final_project.domain.WorkDayInfo;
import com.timetable.final_project.exceptions.NoSuchEmployeeException;
import com.timetable.final_project.helper_classes.SubmitHours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class OverviewHoursEndPoint {

    @Autowired
    WorkDayInfoService workDayInfoService;

    @CrossOrigin
    @RequestMapping(value = "/employee/{id}/overviewHours", method = RequestMethod.GET)
//    public ResponseEntity overviewHours(@PathVariable("id") long id) {
    public List<SubmitHours> overviewHours(@PathVariable("id") long id) {

        List<WorkDayInfo> workDayInfoList = null;
        try {
            List<SubmitHours> submitHoursList = new ArrayList<>();
            workDayInfoList = workDayInfoService.findByEmployeeId(id);
            workDayInfoList.forEach(workDayInfo -> {
                SubmitHours submitHours = new SubmitHours(workDayInfo, 0, "Success");
                submitHoursList.add(submitHours);
            });
            return submitHoursList;
//            return new ResponseEntity<>(submittedHours, HttpStatus.OK);
        } catch (NoSuchEmployeeException e) {
            SubmitHours submitHours = new SubmitHours(1, "Invalid employee id");
            return Arrays.asList(submitHours);
//            return ResponseEntity.badRequest().body("Employee id does not exist");
        }
    }

}
