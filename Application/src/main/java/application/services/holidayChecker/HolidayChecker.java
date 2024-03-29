package application.services.holidayChecker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HolidayChecker {
    private final List<LocalDate> holidays;
    private final String filePath;

    public HolidayChecker() {
        holidays = new ArrayList<>();
        this.filePath = getClass().getResource("/holidays.txt").getPath();
    }

    public void init(){
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("-");
                int day = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);

                int currentYear = LocalDate.now().getYear();

                LocalDate holiday = LocalDate.of(currentYear, month, day);

                holidays.add(holiday);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isHoliday(LocalDate date) {
        return (holidays.contains(date) || date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY);
    }
}
