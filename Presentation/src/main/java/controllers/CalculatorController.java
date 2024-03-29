package controllers;

import application.calculationStrategy.HolidayAwareVacationCalculation;
import application.calculationStrategy.StandartVacationCalculation;
import contracts.ICalculatorService;
import models.moneyCount.Money;
import models.vacationBlank.VacationBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDate;

@org.springframework.stereotype.Controller
@RequestMapping("/calculate")
public class CalculatorController {

    private final ICalculatorService calculatorService;

    @Autowired
    public CalculatorController(ICalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/{salary}/salary/{vacationAmount}")
    public ResponseEntity<BigDecimal> CalculateByDays(@PathVariable("salary") BigDecimal salary, @PathVariable("vacationAmount") Integer days) {
        VacationBlank blank = VacationBlank.builder()
                .holidaysAmount(days)
                .earningsPerYear(new Money(salary))
                .build();

        calculatorService.setCalculationStrategy(new StandartVacationCalculation());
        return new ResponseEntity<>(calculatorService.calculate(blank).getMoney(), HttpStatus.OK);
    }

    @GetMapping("/{salary}/salary/{vacationAmount}/vacationAmount/{startDay}/startDay/{endDay}")
    public ResponseEntity<BigDecimal> CalculateByDays(@PathVariable("salary") BigDecimal salary,
                                                      @PathVariable("vacationAmount") Integer days,
                                                      @PathVariable("startDay") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDay,
                                                      @PathVariable("endDay") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDay) {

        VacationBlank blank = VacationBlank.builder()
                .holidaysAmount(days)
                .earningsPerYear(new Money(salary))
                .startVacation(startDay)
                .endVacation(endDay)
                .build();

        calculatorService.setCalculationStrategy(new HolidayAwareVacationCalculation());
        return new ResponseEntity<>(calculatorService.calculate(blank).getMoney(), HttpStatus.OK);
    }

}
