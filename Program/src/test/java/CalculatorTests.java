import application.calculationStrategy.HolidayAwareVacationCalculation;
import application.calculationStrategy.StandartVacationCalculation;
import application.calculator.VacationSalaryCalculatorService;
import contracts.ICalculatorService;
import models.moneyCount.Money;
import models.vacationBlank.VacationBlank;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CalculatorTests {

    @Test
    public void Calculate_ShouldReturn7000_WhenTwoHolidaysAndOneWeekend_InTenDays() {
        ICalculatorService calculator = new VacationSalaryCalculatorService();

        VacationBlank blank = VacationBlank.builder()
                .startVacation(LocalDate.of(2023, 12, 30))
                .endVacation(LocalDate.of(2024, 1, 8))
                .earningsPerYear(new Money(new BigDecimal(365000)))
                .holidaysAmount(10).build();

        calculator.setCalculationStrategy(new HolidayAwareVacationCalculation());

        Assert.assertEquals(BigDecimal.valueOf(7000), calculator.calculate(blank).getMoney());
    }

    @Test
    public void Calculate_ShouldReturn10000_WhenTenDaysWithoutCalculatingHolidays() {
        ICalculatorService calculator = new VacationSalaryCalculatorService();

        VacationBlank blank = VacationBlank.builder()
                .startVacation(LocalDate.of(2023, 12, 30))
                .endVacation(LocalDate.of(2024, 1, 8))
                .earningsPerYear(new Money(new BigDecimal(365000)))
                .holidaysAmount(10).build();

        calculator.setCalculationStrategy(new StandartVacationCalculation());

        Assert.assertEquals(BigDecimal.valueOf(10000), calculator.calculate(blank).getMoney());
    }
}
