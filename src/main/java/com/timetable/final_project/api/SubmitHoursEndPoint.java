package com.timetable.final_project.api;

import com.timetable.final_project.controller.EmployeeService;
import com.timetable.final_project.controller.WorkDayInfoService;
import com.timetable.final_project.domain.Employee;
import com.timetable.final_project.domain.WorkDayInfo;
import com.timetable.final_project.exceptions.NotEnoughDaysOffException;
import com.timetable.final_project.exceptions.NotValidDateException;
import com.timetable.final_project.helper_classes.SubmitHours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubmitHoursEndPoint {

    @Autowired
    WorkDayInfoService workDayInfoService;

    @Autowired
    EmployeeService employeeService;

    @CrossOrigin
    @RequestMapping(value = "/submitHours", method = RequestMethod.POST)
    public SubmitHours submitWorkDayInfo(@RequestBody SubmitHours submitHours) {

        try {
            workDayInfoService.isValidDate(submitHours);
        } catch (NotValidDateException e) {
            submitHours.setStatusCode(1);
            submitHours.setMessage("Wrong date");
            return submitHours;
        }

        try {
            workDayInfoService.canTakeDayOff(submitHours);
        } catch (NotEnoughDaysOffException e) {
            submitHours.setMessage("Not enough days off");
            submitHours.setStatusCode(2);
            return submitHours;
        }

        workDayInfoService.submitWorkDayInfo(submitHours);
        submitHours.setStatusCode(0);
        submitHours.setMessage("Success");
        return submitHours;
    }
}
