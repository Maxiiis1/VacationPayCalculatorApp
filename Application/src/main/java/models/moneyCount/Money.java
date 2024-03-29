package models.moneyCount;

import java.math.BigDecimal;

public class Money {
    private BigDecimal money;
    public Money(BigDecimal money){
        this.money = money;
    }

    public BigDecimal getMoney() {
        return money;
    }
}
