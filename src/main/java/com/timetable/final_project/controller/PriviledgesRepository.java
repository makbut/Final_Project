package com.timetable.final_project.controller;

import com.timetable.final_project.domain.Privileges;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface PriviledgesRepository extends CrudRepository<Privileges,Long> {
}
