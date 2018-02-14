package com.timetable.final_project.controller;

import com.timetable.final_project.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface RoleRepository extends CrudRepository<Role,Long> {
}