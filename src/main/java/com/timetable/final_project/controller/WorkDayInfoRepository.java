package com.timetable.final_project.controller;

import com.timetable.final_project.domain.Employee;
import com.timetable.final_project.domain.WorkDayInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface WorkDayInfoRepository extends CrudRepository<WorkDayInfo,Long> {

    WorkDayInfo findOneByDateAndEmployee(String date, Employee employee);

    List<WorkDayInfo> findByEmployee(Employee employee);
}
