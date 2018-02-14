package com.timetable.final_project.controller;

import com.timetable.final_project.domain.WorkDayInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class WorkDayInfoService {

    private final WorkDayInfoRepository workDayInfoRepository;

    @Autowired
    public WorkDayInfoService(WorkDayInfoRepository workDayInfoRepository) {
        this.workDayInfoRepository = workDayInfoRepository;
    }

    public WorkDayInfo findByDateAndEmployeeId(LocalDate date, long employeeId){
        return workDayInfoRepository.findOneByDateAndEmployeeId(date, employeeId);
    }
}
