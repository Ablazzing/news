package org.javaacademy.news.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateUtil {
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd_MM_yyyy");


    public LocalDate getDateFromString(String date) {
        return LocalDate.parse(date, DATE_TIME_FORMATTER);
    }

    public String getStringFromDate(LocalDate date) {
        return date.format(DATE_TIME_FORMATTER);
    }
}
