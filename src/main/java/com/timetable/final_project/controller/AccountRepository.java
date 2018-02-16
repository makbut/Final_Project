package com.timetable.final_project.controller;

import com.timetable.final_project.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findByUsernameAndPassword(String username, String password);

    Account findOneByUsername(String username);

}
