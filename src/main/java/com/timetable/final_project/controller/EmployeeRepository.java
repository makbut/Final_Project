package com.timetable.final_project.controller;

import com.timetable.final_project.domain.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public interface EmployeeRepository extends CrudRepository<Employee,Long> {

    Employee findOneByEmailAddress(String mail);

}
