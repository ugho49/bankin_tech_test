package models;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundedSum {

    private final Double amount;

    public RoundedSum(final Double amount) {
        if (amount < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(amount);
        bd = bd.setScale(3, RoundingMode.HALF_UP);
        this.amount = bd.doubleValue();
    }

    public Double getAmount() {
        return amount;
    }
}
