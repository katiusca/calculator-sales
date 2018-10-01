package com.privalia.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Calendar;

@Data
public class Stock {

    private Calendar date;
    private BigDecimal closeValue;
    private BigDecimal openValue;

}
