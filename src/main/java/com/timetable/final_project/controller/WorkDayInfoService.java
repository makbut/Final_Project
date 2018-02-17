package com.timetable.final_project.controller;

import com.timetable.final_project.domain.Employee;
import com.timetable.final_project.domain.WorkDayInfo;
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

    public boolean isValidDate(SubmitHours submitHours) throws NotValidDateException{
        Employee employee = employeeRepository.findOne(submitHours.getEmployeeId());
        WorkDayInfo workDayInfo = workDayInfoRepository.findOneByDateAndEmployee(submitHours.getDate(),employee);
        if(workDayInfo!=null){
            throw new NotValidDateException();
        }
        return true;
    }

    public boolean canTakeDayOff(SubmitHours submitHours) throws NotEnoughDaysOffException{
        if (submitHours.getActivity().equals("dayOff")) {
            Employee employee = employeeRepository.findOne(submitHours.getEmployeeId());
            if (employee.getDaysOff() == 0) {
                throw new NotEnoughDaysOffException();
            }
            employee.setDaysOff(employee.getDaysOff() - 1);
        }
        return true;
    }

    public WorkDayInfo submitWorkDayInfo(SubmitHours submitHours){
        Employee employee = employeeRepository.findOne(submitHours.getEmployeeId());
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
