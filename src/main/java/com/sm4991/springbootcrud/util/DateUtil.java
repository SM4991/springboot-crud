package com.sm4991.springbootcrud.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {
    /**
     * Global date format
     * @return
     */
    public static String getGlobalDateFormat() {
        return "yyyy-MM-dd";
    }

    /**
     * Global date time format
     * @return
     */
    public static String getGlobalDateTimeFormat() {
        return "yyyy-MM-dd'T'HH:mm:ss'Z'";
    }

    /**
     * Get Current Timestamp
     * @return
     */
    public static Timestamp getCurrentTimestamp() {
        Date date = new Date();
        return new Timestamp(date.getTime());
    }

    /**
     * Check if date has valid format
     * @param format
     * @param date
     * @return
     */
    public static Boolean isValidDateFormat(String format, String date) {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setLenient(false);
            Date convertedDate = sdf.parse(date);
            return true;
        } catch(ParseException ex) {
            return false;
        }
    }

    /**
     * Convert date string to sql date format
     * @param format
     * @param date
     * @return
     */
    public static java.sql.Date convertDateStringToSqlDate(String format, String date) {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setLenient(false);
            Date convertedDate = sdf.parse(date);
            return new java.sql.Date(convertedDate.getTime());
        } catch(ParseException ex) {
            return null;
        }
    }

    /**
     * Convert date string to sql date format
     * @param format
     * @param date
     * @return
     */
    public static Date convertDateStringToUtilDate(String format, String date) {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setLenient(false);
            Date convertedDate = sdf.parse(date);
            return new Date(convertedDate.getTime());
        } catch(ParseException ex) {
            return null;
        }
    }

    /**
     * Convert local date time to global format
     * @param dateTime
     * @return
     */
    public static String convertLocalDateTimeToFormat(LocalDateTime dateTime) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(getGlobalDateTimeFormat());
        return fmt.format(dateTime);
    }
}
