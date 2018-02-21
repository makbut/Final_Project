package com.timetable.final_project.helper_classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class DateString {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate stringToLocalDate(String localDate){
        return LocalDate.parse(localDate, formatter);
    }

    public static String localDateToString(LocalDate localDate){
        return localDate.format(formatter);
    }
}
