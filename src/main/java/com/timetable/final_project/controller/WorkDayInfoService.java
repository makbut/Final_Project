package com.timetable.final_project.controller;

import com.timetable.final_project.domain.Employee;
import com.timetable.final_project.domain.WorkDayInfo;
import com.timetable.final_project.enums.Activity;
import com.timetable.final_project.exceptions.InvalidDatesException;
import com.timetable.final_project.exceptions.NoSuchEmployeeException;
import com.timetable.final_project.exceptions.NotEnoughDaysOffException;
import com.timetable.final_project.exceptions.FinalizedDateException;
import com.timetable.final_project.helper_classes.DateString;
import com.timetable.final_project.helper_classes.SubmitHours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class WorkDayInfoService {

    @Autowired
    private WorkDayInfoRepository workDayInfoRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    public WorkDayInfo submitWorkDayInfo(SubmitHours submitHours) throws NotEnoughDaysOffException, FinalizedDateException, NoSuchEmployeeException {

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
                throw new FinalizedDateException();
            }
        }else{
            workDayInfo = new WorkDayInfo();
            save = true;
        }


        if (submitHours.getActivity() == Activity.DAYOFF) {
            if (employee.getDaysOff() == 0) {
                throw new NotEnoughDaysOffException();
            }
            if(submitHours.isFinalized()) {
                employee.setDaysOff(employee.getDaysOff() - 1);
            }
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

    public List<WorkDayInfo> getWorkDayInfoGivenID(long id) throws NoSuchEmployeeException {
        Employee employee = employeeRepository.findOne(id);
        if (employee == null) throw new NoSuchEmployeeException();
        return workDayInfoRepository.findByEmployeeOrderByDateAsc(employee);
    }

    public List<WorkDayInfo> getWorkDayInfoGivenIDandFinal(long id, boolean finalized) throws NoSuchEmployeeException {
        Employee employee = employeeRepository.findOne(id);
        if (employee == null) throw new NoSuchEmployeeException();
        return workDayInfoRepository.findByEmployeeAndFinalizedOrderByDateAsc(employee, finalized);
    }


    public Iterable<SubmitHours> getWorkDayInfoGivenDatesAndID(long id, String startDate, String endDate) throws NoSuchEmployeeException, InvalidDatesException {

        Employee employee = employeeRepository.findOne(id);
        if(employee == null){
            throw new NoSuchEmployeeException();
        }

        LocalDate localStartDate = DateString.stringToLocalDate(startDate.replaceAll("_", "/"));
        LocalDate localEndDate = DateString.stringToLocalDate(endDate.replaceAll("_", "/"));

        if(localStartDate.isAfter(localEndDate)){
            throw new InvalidDatesException();
        }

        List<SubmitHours> submitHours = new ArrayList();
        List<WorkDayInfo> workDayInfos = workDayInfoRepository.findByDateBetweenAndEmployeeOrderByDateAsc
                                                                    (localStartDate,localEndDate, employee);

        for(WorkDayInfo wdi : workDayInfos){
            submitHours.add(new SubmitHours(wdi,0,"success"));
        }

        return submitHours;
    }

}
