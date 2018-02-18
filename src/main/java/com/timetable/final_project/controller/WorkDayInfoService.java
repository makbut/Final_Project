package com.timetable.final_project.controller;

import com.timetable.final_project.domain.Employee;
import com.timetable.final_project.domain.WorkDayInfo;
import com.timetable.final_project.enums.Activity;
import com.timetable.final_project.exceptions.NotEnoughDaysOffException;
import com.timetable.final_project.exceptions.NotValidDateException;
import com.timetable.final_project.helper_classes.SubmitHours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class WorkDayInfoService {

    @Autowired
    private WorkDayInfoRepository workDayInfoRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    public WorkDayInfo submitWorkDayInfo(SubmitHours submitHours) throws NotEnoughDaysOffException, NotValidDateException {
        Employee employee = employeeRepository.findOne(submitHours.getEmployeeId());
        WorkDayInfo workDayInfo = workDayInfoRepository.findOneByDateAndEmployee(submitHours.getDate(),employee);

        if(workDayInfo!=null){
            throw new NotValidDateException();
        }

        if (submitHours.getActivity()== Activity.DAYOFF) {
            if (employee.getDaysOff() == 0) {
                throw new NotEnoughDaysOffException();
            }
            employee.setDaysOff(employee.getDaysOff() - 1);
        }

        WorkDayInfo wdi = new WorkDayInfo(
                employee,
                submitHours.getDate(),
                submitHours.getWorkplace(),
                submitHours.getActivity(),
                submitHours.getHours()
        );

        return workDayInfoRepository.save(wdi);

    }

}
