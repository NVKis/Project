package utils;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.experimental.UtilityClass;



@UtilityClass
public class DateHelper {

    public Map<String, Integer> getMonths() {
        Map<String, Integer> monthMap = new HashMap<>();
        monthMap.put("января", 1);
        monthMap.put("февраля", 2);
        monthMap.put("марта", 3);
        monthMap.put("апреля", 4);
        monthMap.put("мая", 5);
        monthMap.put("июня", 6);
        monthMap.put("июля", 7);
        monthMap.put("августа", 8);
        monthMap.put("сентября", 9);
        monthMap.put("октября", 10);
        monthMap.put("ноября", 11);
        monthMap.put("декабря", 12);

        return monthMap;
    }

    public LocalDate getDate(String dateStr) {
        Map<String, Integer> monthMap = getMonths();
        String[] parts = dateStr.split(" ");

        if (parts.length != 2) {
            throw new IllegalArgumentException("Неправльный формат даты. Формат должен быть: 'd MMMM'.");
        }

        int day;
        try {
            day = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неправльный формат даты: " + parts[0], e);
        }

        String monthStr = parts[1];
        Integer month = monthMap.get(monthStr);
        if (month == null) {
            throw new IllegalArgumentException("Неправльный формат даты: " + monthStr);
        }

        return LocalDate.of(LocalDate.now().getYear(), month, day);
    }
}
