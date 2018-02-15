package com.timetable.final_project.controller;


import com.timetable.final_project.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee getEmployeeById(Long id){
        return employeeRepository.findOne(id);
    }

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeByMail(String mail) {
        return employeeRepository.findOneByEmailAddress(mail);
    }
}
