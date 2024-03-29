package application.calculationStrategy;

import application.services.holidayChecker.HolidayChecker;
import models.moneyCount.Money;
import models.vacationBlank.VacationBlank;

import java.math.BigDecimal;

public class HolidayAwareVacationCalculation implements ICalculationStrategy{
    private final HolidayChecker holidayChecker;
    private final Integer daysPerYear;

    public HolidayAwareVacationCalculation(){
        this.holidayChecker = new HolidayChecker();
        daysPerYear = 365;
    }
    @Override
    public Money calculate(VacationBlank vacationBlank) {
        Integer holidaysAmount = vacationBlank.getHolidaysAmount();
        BigDecimal dailySalary = vacationBlank.getEarningsPerYear().getMoney().divide(BigDecimal.valueOf(daysPerYear));
        holidayChecker.init();

        for (int i = 0; i < holidaysAmount; i++) {
            if (holidayChecker.isHoliday(vacationBlank.getStartVacation().plusDays(i))) {
                holidaysAmount--;
            }
        }
        return new Money(dailySalary.multiply(BigDecimal.valueOf(holidaysAmount)));
    }
}
