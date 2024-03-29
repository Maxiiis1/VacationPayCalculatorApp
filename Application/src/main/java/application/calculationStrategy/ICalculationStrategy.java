package application.calculationStrategy;

import models.moneyCount.Money;
import models.vacationBlank.VacationBlank;

public interface ICalculationStrategy {
    Money calculate(VacationBlank vacationBlank);
}
