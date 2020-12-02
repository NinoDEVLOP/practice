package com.practice.demo.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static java.time.ZoneOffset.UTC;

public class DateUtil {

    /**
     * result = secondDate - firstDate;
     */
    public static int betweenDays(Instant firstDate, Instant secondDate) {
        LocalDate f = firstDate.atOffset(UTC).toLocalDate();
        LocalDate s = secondDate.atOffset(UTC).toLocalDate();
        return (int) ChronoUnit.DAYS.between(f, s);
    }

    public static int betweenMonths(Instant firstDate, Instant secondDate) {
        LocalDate f = firstDate.atOffset(UTC).toLocalDate().withDayOfMonth(1);
        LocalDate s = secondDate.atOffset(UTC).toLocalDate().withDayOfMonth(1);
        return (int) ChronoUnit.MONTHS.between(f, s);
    }

    public static int betweenYears(Instant firstDate, Instant secondDate) {
        LocalDate f = firstDate.atOffset(UTC).toLocalDate().withDayOfYear(1);
        LocalDate s = secondDate.atOffset(UTC).toLocalDate().withDayOfYear(1);
        return (int) ChronoUnit.YEARS.between(f, s);
    }

    public static Instant getCurrentDateIncludeToday() {
        LocalDate localDate = Instant.now().atOffset(UTC).toLocalDate().plusDays(1);
        return localDate.atStartOfDay().atOffset(UTC).toInstant();
    }

    public static Instant getCurrentDateExceptedToday() {
        LocalDate localDate = Instant.now().atOffset(UTC).toLocalDate();
        return localDate.atStartOfDay().atOffset(UTC).toInstant();
    }

    public static Instant plusDays(int days) {
        LocalDate localDate = Instant.now().atOffset(UTC).toLocalDate().plusDays(days);
        return localDate.atStartOfDay().atOffset(UTC).toInstant();
    }

    public static Instant plusDays(Instant date, int days) {
        LocalDate localDate = date.atOffset(UTC).toLocalDate().plusDays(days);
        return localDate.atStartOfDay().atOffset(UTC).toInstant();
    }

    public static Instant minusDays(Instant date, int days) {
        LocalDate localDate = date.atOffset(UTC).toLocalDate().minusDays(days);
        return localDate.atStartOfDay().atOffset(UTC).toInstant();
    }

    public static Instant plusMonths(int months) {
        LocalDate localDate = Instant.now().atOffset(UTC).toLocalDate().plusMonths(months).withDayOfMonth(1);
        return localDate.atStartOfDay().atOffset(UTC).toInstant();
    }

    public static Instant plusMonths(Instant date, int months) {
        LocalDate localDate = date.atOffset(UTC).toLocalDate().plusMonths(months).withDayOfMonth(1);
        return localDate.atStartOfDay().atOffset(UTC).toInstant();
    }

    public static Instant minusMonths(Instant date, int months) {
        LocalDate localDate = date.atOffset(UTC).toLocalDate().minusMonths(months).withDayOfMonth(1);
        return localDate.atStartOfDay().atOffset(UTC).toInstant();
    }

    public static Instant plusYears(int years) {
        LocalDate localDate =
                Instant.now().atOffset(UTC).toLocalDate().plusYears(years).withDayOfMonth(1);
        return localDate.atStartOfDay().atOffset(UTC).toInstant();
    }

    public static Instant plusYears(Instant date, int years) {
        LocalDate localDate = date.atOffset(UTC).toLocalDate().plusYears(years).withDayOfYear(1);
        return localDate.atStartOfDay().atOffset(UTC).toInstant();
    }

    public static Instant minusYears(Instant date, int years) {
        LocalDate localDate = date.atOffset(UTC).toLocalDate().minusYears(years).withDayOfYear(1);
        return localDate.atStartOfDay().atOffset(UTC).toInstant();
    }

    public static Instant maxTimeOfDay(Instant date) {
        LocalDate localDate = date.atOffset(UTC).toLocalDate().plusDays(1);
        return localDate.atStartOfDay().minusNanos(1).atOffset(UTC).toInstant();
    }

    public static Instant minTimeOfDay(Instant date) {
        return date.atOffset(UTC).toLocalDate().atStartOfDay().atOffset(UTC).toInstant();
    }

    public static Instant getCurrentMonthFirstDay() {
        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        Instant firstday = cale.toInstant().atOffset(UTC).toLocalDate().atStartOfDay().atOffset(UTC).toInstant();
        return firstday;
    }

    public static Instant minTimeOfMonth(Instant date) {
        return date.atOffset(UTC).toLocalDate().withDayOfMonth(1).atStartOfDay().atOffset(UTC).toInstant();
    }

    public static Instant maxTimeOfMonth(Instant date) {
        return date.atOffset(UTC).toLocalDate().plusMonths(1).withDayOfMonth(1).atStartOfDay().minusNanos(1).atOffset(UTC).toInstant();
    }

    public static Instant minTimeOfYear(Instant date) {
        return date.atOffset(UTC).toLocalDate().withDayOfYear(1).atStartOfDay().atOffset(UTC).toInstant();
    }

    public static Instant maxTimeOfYear(Instant date) {
        return date.atOffset(UTC).toLocalDate().plus(1, ChronoUnit.YEARS).withDayOfYear(1).atStartOfDay().minusNanos(1).atOffset(UTC).toInstant();
    }

    public static String formatyyyyMMdd(Instant instant) {
        return instant.atOffset(UTC).toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static String format(Instant instant, String pattern) {
        return instant.atOffset(UTC).toLocalDate().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String format(Long time, String pattern) {
        return Instant.ofEpochMilli(time).atOffset(UTC).toLocalDate().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatyyyyMMddwithoutSeperation(Instant instant) {
        return instant.atOffset(UTC).toLocalDate().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public static String formatyyyyMMddwithoutSeperation(Long time) {
        return Instant.ofEpochMilli(time).atOffset(UTC).toLocalDate().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    /**
     * Instant gets the 13 bit number of milliseconds of type long
     * @param instant
     * @return
     */
    public static Long instantToLongMilli(Instant instant) {
        return instant.plusMillis(TimeUnit.HOURS.toMillis(8)).toEpochMilli();
    }

    /**
     * Instant gets the 10 bit seconds of long type
     * @param instant
     * @return
     */
    public static Long intantToLongSecond(Instant instant) {
        return instant.plusMillis(TimeUnit.HOURS.toMillis(8)).getEpochSecond();
    }

}
