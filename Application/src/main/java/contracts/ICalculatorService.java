package contracts;

import application.calculationStrategy.ICalculationStrategy;
import models.moneyCount.Money;
import models.vacationBlank.VacationBlank;

public interface ICalculatorService {
    void setCalculationStrategy(ICalculationStrategy calculationStrategy);
    Money calculate(VacationBlank vacationBlank);
}
