package application.calculationStrategy;

import models.moneyCount.Money;
import models.vacationBlank.VacationBlank;

import java.math.BigDecimal;

public class StandartVacationCalculation implements ICalculationStrategy{

    private final Integer daysPerYear = 365;
    @Override
    public Money calculate(VacationBlank vacationBlank) {

        BigDecimal dailySalary = vacationBlank.getEarningsPerYear().getMoney().divide(BigDecimal.valueOf(daysPerYear));
        return new Money(dailySalary.multiply(BigDecimal.valueOf(vacationBlank.getHolidaysAmount())));
    }
}
