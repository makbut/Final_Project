package com.timetable.final_project.api;


import com.timetable.final_project.controller.WorkDayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OverviewHoursEndPoint {

    @Autowired
    WorkDayInfoService workDayInfoService;

    @CrossOrigin
    @RequestMapping(value = "/overviewHours/{id}", method = RequestMethod.GET)
    public void overviewHours(@PathVariable("id") int id){


    }

}
