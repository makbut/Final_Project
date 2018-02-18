package com.timetable.final_project.controller;


import com.timetable.final_project.exceptions.EmailExistsException;
import com.timetable.final_project.domain.Employee;
import com.timetable.final_project.helper_classes.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

}
