package com.privalia.busines;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculateInvest{

    public BigDecimal moneyToInvest = new BigDecimal("50.0").multiply(new BigDecimal("0.98"));
    public static BigDecimal finalInvest = new BigDecimal("0.0");

    public void calculateInvest(BigDecimal priceToDay) {
        finalInvest = finalInvest.add(moneyToInvest.divide(priceToDay, 3, RoundingMode.FLOOR ));
    }
}
