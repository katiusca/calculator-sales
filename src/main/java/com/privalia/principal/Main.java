package com.privalia.principal;

import com.privalia.busines.CalculateInvest;
import com.privalia.model.Stock;
import com.privalia.util.CalendarUtils;
import com.privalia.util.CsvReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

import static com.privalia.busines.CalculateInvest.finalInvest;
import static com.privalia.util.CalculateDaysByMonth.lastFridayByMonth;
import static com.privalia.util.CsvReader.dateInit;
import static com.privalia.util.CsvReader.stocks;
import static com.privalia.util.CsvReader.dateEnd;

public class Main {

    public static void main(String[] args) {
        CalculateInvest calculateInvest = new CalculateInvest();
        CsvReader csvReader = new CsvReader();
        csvReader.readFile();
        for(Calendar dayOfMonth : lastFridayByMonth){

            Stock stockFound = stocks.stream()
                    .filter(s -> dayOfMonth.equals(s.getDate())).findAny().orElse(null);

            if (stockFound != null) {
                calculateInvest.calculateInvest(stockFound.getOpenValue());
            }else {
                do {
                    dayOfMonth.add(Calendar.DAY_OF_MONTH, 1);
                    stockFound = stocks.stream()
                            .filter(s -> dayOfMonth.equals(s.getDate())).findAny().orElse(null);

                } while(stockFound == null);
                calculateInvest.calculateInvest(stockFound.getOpenValue());
            }
        }
        Stock stockForSale = stocks.stream()
                .filter(s -> CalendarUtils.getCalendarFromString(dateEnd).equals(s.getDate())).findAny().orElse(null);
        BigDecimal sale = stockForSale.getCloseValue().multiply(finalInvest).setScale(3,RoundingMode.FLOOR);
        String messages = new StringBuilder()
                .append("Date of start: ").append(dateInit)
                .append(" Total money invest: ").append(finalInvest)
                .append(" Date of end: ").append(dateEnd)
                .append(" Money by sales: ").append(sale).toString();

        System.out.println(messages);
    }
}
