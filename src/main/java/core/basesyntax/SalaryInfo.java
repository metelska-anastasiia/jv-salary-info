package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private static final int INDEX_DATE = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_HOURS = 2;
    private static final int INDEX_INCOME = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromLocal = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToLocal = LocalDate.parse(dateTo, FORMATTER);
        String[] dataNew;
        StringBuilder statistic = new StringBuilder();

        for (String name : names) {
            int salary = 0;
            for (String date : data) {
                dataNew = date.split(" ");
                LocalDate dateLocal = LocalDate.parse(dataNew[INDEX_DATE], FORMATTER);
                String nameData = dataNew[INDEX_NAME];
                if (name.equals(nameData)
                        && (dateLocal.isEqual(dateFromLocal) || dateLocal.isAfter(dateFromLocal))
                        && (dateLocal.isEqual(dateToLocal) || dateLocal.isBefore(dateToLocal))) {
                    salary = salary + Integer.parseInt(dataNew[INDEX_HOURS].trim())
                            * Integer.parseInt(dataNew[INDEX_INCOME].trim());
                }
            }
            statistic.append(System.lineSeparator()).append(name)
                    .append(" - ").append(salary);
        }
        return "Report for period " + dateFrom + " - " + dateTo + statistic;
    }
}
