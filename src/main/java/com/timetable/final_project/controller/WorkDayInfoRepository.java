package com.timetable.final_project.controller;

import com.timetable.final_project.domain.WorkDayInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface WorkDayInfoRepository extends CrudRepository<WorkDayInfo,Long> {
}
