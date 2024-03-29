package models.vacationBlank;

import lombok.Builder;
import lombok.Data;
import models.moneyCount.Money;

import java.time.LocalDate;

@Data
@Builder
public class VacationBlank {
    private Integer holidaysAmount;
    private Money earningsPerYear;
    private LocalDate startVacation;
    private LocalDate endVacation;
}
