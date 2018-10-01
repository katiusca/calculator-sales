package com.privalia.util;

import java.text.DateFormatSymbols;
import java.util.*;

public class CalculateDaysByMonth {

    public static List<Calendar> lastFridayByMonth = new ArrayList<>();

    public void getLastDayByMonth(String dateInit, String dateEnd) {
        Locale locale = new Locale("es", "ES");

        Calendar initCalendar = CalendarUtils.getCalendarFromString(dateInit);
        Calendar endCalendar = CalendarUtils.getCalendarFromString(dateEnd);

        initCalendar.set(Calendar.DAY_OF_MONTH, 1);
        endCalendar.set(Calendar.DAY_OF_MONTH, 1);
        while(initCalendar.before(endCalendar)) {

            Calendar lastDayOfMonth = Calendar.getInstance();
            lastDayOfMonth.set(Calendar.YEAR, initCalendar.get(Calendar.YEAR)); //new GregorianCalendar(initCalendar.get(Calendar.YEAR),initCalendar.get(Calendar.MONTH),1);
            lastDayOfMonth.set(Calendar.MONTH, initCalendar.get(Calendar.MONTH));
            int totalDaysOfMonth = lastDayOfMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
            lastDayOfMonth.set(Calendar.DAY_OF_MONTH, totalDaysOfMonth);
            int daysToRollBack = (lastDayOfMonth.get(Calendar.DAY_OF_WEEK) + 1) % 7;
            int day = totalDaysOfMonth - daysToRollBack;
            lastDayOfMonth.set(Calendar.DAY_OF_MONTH, day);
            String month = new DateFormatSymbols(locale).getShortMonths()[initCalendar.get(Calendar.MONTH)];

            String lastFriday = day +"-"+ month +"-"+ initCalendar.get(Calendar.YEAR);
            lastFridayByMonth.add(CalendarUtils.getCalendarFromString(lastFriday));

            initCalendar.add(Calendar.MONTH, 1);
        }
    }
}
