package com.example.demo;

import java.time.*;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * @Package: com.example.demo
 * @Description:java8 日期时间练习
 * @Author: LuDeSong
 * @Date: 2021-4-1 11:00
 */

public class Java8DateTime {

    public static void main(String[] args) {
        localDateTime();
    }

    private static void localDateTime(){

        //localDate
        LocalDate localDate = LocalDate.now();
        LocalDate date = LocalDate.of(2021, 4, 1);
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek dow = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leap = date.isLeapYear();

        //localTime
        LocalTime localTime = LocalTime.now();
        LocalTime time1 = LocalTime.of(13, 45);
        LocalTime time = LocalTime.of(13, 45, 20);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        try {
            LocalDate date0 = LocalDate.parse("2014-03-18");
            LocalTime time0 = LocalTime.parse("13:45:20");
        }catch (DateTimeParseException e){
            System.out.println("format error");
            e.printStackTrace();
        }

        //localDateTime
        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(0, 45, 55);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);

        Duration d1 = Duration.between(time1, time);
        Duration d2 = Duration.between(dt1, dt2);
        Period tenDays = Period.between(LocalDate.of(2014, 3, 8),
                LocalDate.of(2014, 3, 18));

        Duration threeMinutes = Duration.ofMinutes(3);
        Duration threeMinutes1 = Duration.of(3, ChronoUnit.MINUTES);
        Period tenDays1 = Period.ofDays(10);
        Period threeWeeks = Period.ofWeeks(3);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
    }
}
