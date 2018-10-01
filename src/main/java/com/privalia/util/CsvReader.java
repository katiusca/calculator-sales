package com.privalia.util;

import com.privalia.model.Stock;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CsvReader {
    static Properties properties;
    static FileInputStream inputStream;
    public static List<Stock> stocks= new ArrayList<>();
    public static String dateEnd = "";
    public static String dateInit = "";
    static {
        properties = new Properties();
        try {
            inputStream = new FileInputStream("src/main/resources/config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    public void readFile() {
        String cvsSplitBy = ";";
        CalculateDaysByMonth calculateDaysByMonth = new CalculateDaysByMonth();
        int count = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(properties.getProperty("filename")))) {
            String currentLine = bufferedReader.readLine();
            String nextLine = bufferedReader.readLine();
            while (currentLine != null) {
                String[] value = currentLine.split(cvsSplitBy);
                if (count == 1) {
                    dateEnd = value[0];
                }
                if (count != 0) {
                    Stock stock = new Stock();
                    stock.setDate(CalendarUtils.getCalendarFromString(value[0]));
                    stock.setCloseValue(new BigDecimal(value[1]).setScale(3));
                    stock.setOpenValue(new BigDecimal(value[2]).setScale(3));
                    stocks.add(stock);
                }
                if(null == nextLine) {
                    dateInit = value[0];
                }
                currentLine = nextLine;
                nextLine = bufferedReader.readLine();
                count++;
            }
            calculateDaysByMonth.getLastDayByMonth(dateInit, dateEnd);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}