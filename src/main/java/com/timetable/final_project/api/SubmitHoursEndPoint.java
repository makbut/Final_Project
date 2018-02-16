package com.timetable.final_project.api;

import com.timetable.final_project.controller.EmployeeService;
import com.timetable.final_project.controller.WorkDayInfoService;
import com.timetable.final_project.domain.Employee;
import com.timetable.final_project.domain.WorkDayInfo;
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
        Employee employee = employeeService.getEmployeeById(submitHours.getEmployeeId());
        WorkDayInfo wdi = workDayInfoService.getWorkDayByDateAndEmployee(submitHours.getDate(),employee);
        if(wdi == null){
            if(submitHours.getActivity().equals("dayOff")){
                if(employee.getDaysOff()==0){
                    submitHours.setMessage("Not enough days off");
                    submitHours.setStatusCode(2);
                    return submitHours;
                }else{
                    employee.setDaysOff(employee.getDaysOff()-1);
                }
            }
            WorkDayInfo wdi1 = new WorkDayInfo(
                                                employee,
                                                submitHours.getDate(),
                                                submitHours.getWorkplace(),
                                                submitHours.getActivity(),
                                                submitHours.getHours()
                                              );
            workDayInfoService.saveDayInformation(wdi1);
            submitHours.setStatusCode(0);
            submitHours.setMessage("Success");
            return submitHours;
        }else {
            submitHours.setStatusCode(1);
            submitHours.setMessage("Wrong date");
            return submitHours;
        }
    }
}
