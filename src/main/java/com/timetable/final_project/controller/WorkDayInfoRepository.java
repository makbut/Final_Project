package com.timetable.final_project.controller;

import com.timetable.final_project.domain.Employee;
import com.timetable.final_project.domain.WorkDayInfo;
import org.apache.tomcat.jni.Local;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public interface WorkDayInfoRepository extends CrudRepository<WorkDayInfo,Long> {

    WorkDayInfo findOneByDateAndEmployee(LocalDate date, Employee employee);

    List<WorkDayInfo> findByEmployeeOrderByDateAsc(Employee employee);

    List<WorkDayInfo> findByEmployeeAndFinalizedOrderByDateAsc(Employee employee, boolean finalized);

    List<WorkDayInfo> findByDateBetweenAndEmployeeOrderByDateAsc(LocalDate date1, LocalDate date2, Employee employee);

}
