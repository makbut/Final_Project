package com.timetable.final_project.api;

import com.timetable.final_project.controller.WorkDayInfoService;
import com.timetable.final_project.exceptions.NoSuchEmployeeException;
import com.timetable.final_project.exceptions.NotEnoughDaysOffException;
import com.timetable.final_project.exceptions.FinalizedDateException;
import com.timetable.final_project.helper_classes.SubmitHours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubmitHoursEndPoint {

    @Autowired
    WorkDayInfoService workDayInfoService;

    @CrossOrigin
    @RequestMapping(value = "/submitHours", method = RequestMethod.POST)
    public ResponseEntity submitWorkDayInfo(@RequestBody SubmitHours submitHours) {

        try {
            workDayInfoService.submitWorkDayInfo(submitHours);
            submitHours.setStatusCode(0);
            submitHours.setMessage("Success");
            return ResponseEntity.status(HttpStatus.OK).body(submitHours);
        } catch (NotEnoughDaysOffException e) {
            submitHours.setMessage("Not enough days off");
            submitHours.setStatusCode(1);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(submitHours);
        } catch (FinalizedDateException e) {
            submitHours.setStatusCode(2);
            submitHours.setMessage("This date is finalized");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(submitHours);
        } catch (NoSuchEmployeeException e) {
            submitHours.setStatusCode(3);
            submitHours.setMessage("No such employee");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(submitHours);
        }

    }
}
