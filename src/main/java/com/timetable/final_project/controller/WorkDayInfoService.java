package com.timetable.final_project.controller;

import com.timetable.final_project.domain.Employee;
import com.timetable.final_project.domain.WorkDayInfo;
import com.timetable.final_project.enums.Activity;
import com.timetable.final_project.exceptions.NoSuchEmployeeException;
import com.timetable.final_project.exceptions.NotEnoughDaysOffException;
import com.timetable.final_project.exceptions.NotValidDateException;
import com.timetable.final_project.helper_classes.DateString;
import com.timetable.final_project.helper_classes.SubmitHours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class WorkDayInfoService {

    @Autowired
    private WorkDayInfoRepository workDayInfoRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    public WorkDayInfo submitWorkDayInfo(SubmitHours submitHours) throws NotEnoughDaysOffException, NotValidDateException, NoSuchEmployeeException {

        Employee employee = employeeRepository.findOne(submitHours.getEmployeeId());
        if (employee == null) {
            throw new NoSuchEmployeeException();
        }

        boolean save = false;

        submitHours.setLastWorkplace(employee.getLastWorkplace());
        LocalDate localDate = DateString.stringToLocalDate(submitHours.getDate());
        WorkDayInfo workDayInfo = workDayInfoRepository.findOneByDateAndEmployee(localDate, employee);

        if (workDayInfo != null) {
            if(workDayInfo.isFinalized()){
                throw new NotValidDateException();
            }
        }else{
            workDayInfo = new WorkDayInfo();
            save = true;
        }


        if (submitHours.getActivity() == Activity.DAYOFF) {
            if (employee.getDaysOff() == 0) {
                throw new NotEnoughDaysOffException();
            }
            employee.setDaysOff(employee.getDaysOff() - 1);
        }

        workDayInfo.copyInfo(employee,
                localDate,
                submitHours.getWorkplace(),
                submitHours.getActivity(),
                submitHours.getHours(),
                submitHours.isFinalized());
        employee.setLastWorkplace(submitHours.getWorkplace());
        submitHours.setLastWorkplace(submitHours.getWorkplace());

        if(save) {
            return workDayInfoRepository.save(workDayInfo);
        }else{
            return workDayInfo;
        }

    }

    public List<WorkDayInfo> findByEmployeeId(long id) throws NoSuchEmployeeException {
        Employee employee = employeeRepository.findOne(id);
        if (employee == null) throw new NoSuchEmployeeException();
        return workDayInfoRepository.findByEmployee(employee);
    }

    public Iterable<SubmitHours> getWorkDayInfoGivenDatesAndID(long id, String startDate, String endDate) throws NoSuchEmployeeException {

        Employee employee = employeeRepository.findOne(id);
        if(employee == null){
            throw new NoSuchEmployeeException();
        }

        List<SubmitHours> submitHours = new ArrayList();
        List<WorkDayInfo> workDayInfos =  workDayInfos = workDayInfoRepository.findByDateBetweenAndEmployee(
                DateString.stringToLocalDate(startDate.replaceAll("_", "/")),
                DateString.stringToLocalDate(endDate.replaceAll("_", "/")),
                employee);

        for(WorkDayInfo wdi : workDayInfos){
            submitHours.add(new SubmitHours(wdi,0,"success"));
        }

        return submitHours;
    }

}
