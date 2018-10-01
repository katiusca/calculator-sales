package com.privalia.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendarUtils {

    public static Calendar getCalendarFromString(String dateInFormatString) {
        Locale locale = new Locale("es", "ES");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", locale);
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = formatter.parse(dateInFormatString);
            calendar.setTime(date);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return calendar;
    }
}
