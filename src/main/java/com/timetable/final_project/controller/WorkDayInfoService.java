package com.timetable.final_project.controller;

import com.timetable.final_project.domain.Employee;
import com.timetable.final_project.domain.WorkDayInfo;
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

    public WorkDayInfo getWorkDayByDateAndEmployee(String date, Employee employee){
        return workDayInfoRepository.findOneByDateAndEmployee(date,employee);
    }

    public Iterable<WorkDayInfo> getWorkDaysByEmployee(Employee employee){
        return workDayInfoRepository.findByEmployee(employee);
    }

    public WorkDayInfo saveDayInformation(WorkDayInfo workDayInfo){
        return workDayInfoRepository.save(workDayInfo);
    }
}
