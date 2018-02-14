package com.timetable.final_project.controller;

import com.timetable.final_project.domain.Employee;
import com.timetable.final_project.domain.WorkDayInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
public interface WorkDayInfoRepository extends CrudRepository<WorkDayInfo,Long> {
  /*  @Transactional
    @Modifying
    @Query()
    List<WorkDayInfo> getDateById(Date date);

    @Query(value = "SELECT date FROM WorkDayInfo ")
    List<WorkDayInfo> getDateById();*/

    WorkDayInfo findOneByDateAndEmployeeId(LocalDate date, long employeeId);
}
