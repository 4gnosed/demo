package com.example.demo;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.TimeZone;

/**
 * @Package: com.example.demo
 * @Description:java8 日期时间练习
 * @Author: LuDeSong
 * @Date: 2021-4-1 11:00
 */

public class Java8DateTime {

    public static void main(String[] args) {
        formatDateTime();
    }

    private static void formatDateTime(){
        LocalDate date = LocalDate.of(2014, 3, 18);
        String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);//20140318
        String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);//2014-03-18

        LocalDate date1 = LocalDate.parse("20140318",
                DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate date2 = LocalDate.parse("2014-03-18",
                DateTimeFormatter.ISO_LOCAL_DATE);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date.format(formatter);
        LocalDate date4 = LocalDate.parse(formattedDate, formatter);


        ZoneId zoneId = TimeZone.getDefault().toZoneId();

        ZoneId romeZone = ZoneId.of("America/New_York");
        ZonedDateTime zdt1 = date.atStartOfDay(romeZone);
        LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
        ZonedDateTime zdt2 = dateTime.atZone(romeZone);
        Instant instant = Instant.now();
        ZonedDateTime zdt3 = instant.atZone(romeZone);

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

        LocalDate date1 = LocalDate.of(2001, 2, 27);
        LocalDate date5 = date1.plusDays(2);
        LocalDate date2 = date1.plusWeeks(2);
        LocalDate date3 = date2.minusYears(3);
        LocalDate date4 = date3.plus(6, ChronoUnit.MONTHS);


        TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(
                temporal -> {
                    DayOfWeek dow1 =
                            DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
                    int dayToAdd = 1;
                    if (dow1 == DayOfWeek.FRIDAY) dayToAdd = 3;
                    if (dow1 == DayOfWeek.SATURDAY) dayToAdd = 2;
                    return temporal.plus(dayToAdd, ChronoUnit.DAYS);
                });
        date = date.with(nextWorkingDay);
    }
}
