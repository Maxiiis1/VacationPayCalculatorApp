package application.calculator;

import application.calculationStrategy.ICalculationStrategy;
import contracts.ICalculatorService;
import models.moneyCount.Money;
import models.vacationBlank.VacationBlank;
import org.springframework.stereotype.Service;

@Service
public class VacationSalaryCalculatorService implements ICalculatorService {
    private ICalculationStrategy calculationStrategy;

    @Override
    public void setCalculationStrategy(ICalculationStrategy calculationStrategy) {
        this.calculationStrategy = calculationStrategy;
    }

    @Override
    public Money calculate(VacationBlank vacationBlank) {
        return this.calculationStrategy.calculate(vacationBlank);
    }
}
