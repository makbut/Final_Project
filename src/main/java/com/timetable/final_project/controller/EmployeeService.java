package com.timetable.final_project.controller;


import com.timetable.final_project.exceptions.EmailNotExistsException;
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

    public Employee registerEmployee(UserRegistration userRegistration){

        Employee employee = new Employee(
                userRegistration.getFirstName(),
                userRegistration.getLastName(),
                userRegistration.getEmailAddress(),
                userRegistration.getRole(),
                userRegistration.getHourlyWage(),
                userRegistration.getDaysOff()
        );

        return employeeRepository.save(employee);
    }

    public Employee getEmployeeByMail(String mail) throws EmailNotExistsException {
        Employee employee =  employeeRepository.findOneByEmailAddress(mail);
        if(employee == null){
            throw new EmailNotExistsException();
        }
        return employee;
    }
}
