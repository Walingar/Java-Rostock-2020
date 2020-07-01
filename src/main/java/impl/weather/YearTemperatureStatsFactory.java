package impl.weather;

import api.weather.DayTemperatureInfo;
import api.weather.YearTemperatureStats;

import java.time.Month;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class YearTemperatureStatsFactory implements YearTemperatureStats {
    private static List<List<DayTemperatureInfo>> dateTemperature;

    public static YearTemperatureStats getInstance() {
        dateTemperature = new LinkedList<>();
        for (Month month : Month.values()) {
            List<DayTemperatureInfo> eachMonth = new LinkedList<>();
            for (int day = 0; day < month.length(false); day++) {
                eachMonth.add(null);
            }
            dateTemperature.add(eachMonth);
        }
        return new YearTemperatureStatsFactory();
    }

    @Override
    public void updateStats(DayTemperatureInfo info) {
        int dayIndex = info.getDay() - 1;
        Month month = info.getMonth();
        int monthIndex = month.getValue() - 1;
        List<DayTemperatureInfo> monthList = dateTemperature.get(monthIndex);
        monthList.set(dayIndex, info);
        dateTemperature.set(monthIndex, monthList);
    }

    @Override
    public Double getAverageTemperature(Month month) {
        int monthIndex = month.getValue() - 1;
        List<DayTemperatureInfo> monthList = dateTemperature.get(monthIndex);
        double sum = 0;
        int daysKnown = 0;
        int checkEmptyMonth = 0;
        for (DayTemperatureInfo day : monthList) {
            if (day != null) {
                sum += day.getTemperature();
                daysKnown += 1;
            } else {
                checkEmptyMonth += 1;
            }
        }
        if (checkEmptyMonth == month.length(false)) {
            return null;
        } else return sum / daysKnown;
    }

    @Override
    public Map<Month, Integer> getMaxTemperature() {
        Map<Month, Integer> maxTemperatures = new HashMap<>();
        for (List<DayTemperatureInfo> monthList : dateTemperature) {
            int monthNumber = dateTemperature.indexOf(monthList) + 1;
            Month month = Month.of(monthNumber);
            Integer eachMax = null;
            int checkEmptyMonth = 0;
            for (DayTemperatureInfo day : monthList) {
                if (day != null) {
                    int dayTemperature = day.getTemperature();
                    if (eachMax == null || eachMax < dayTemperature) {
                        eachMax = dayTemperature;
                    }
                } else {
                    checkEmptyMonth += 1;
                }
            }
            if (checkEmptyMonth != month.length(false)) {
                maxTemperatures.put(month, eachMax);
            }
        }
        return maxTemperatures;
    }

    @Override
    public List<DayTemperatureInfo> getSortedTemperature(Month month) {
        int monthIndex = month.getValue() - 1;
        List<DayTemperatureInfo> monthList = dateTemperature.get(monthIndex);
        List<DayTemperatureInfo> output = new LinkedList<>();
        int elementCount = 0;
        for (DayTemperatureInfo currentElement : monthList) {
            if (currentElement != null) {
                if (elementCount == 0) {
                    output.add(currentElement);
                } else {
                    int previousIndex = elementCount - 1;
                    DayTemperatureInfo previousElement = output.get(previousIndex);
                    int previousTemperature = previousElement.getTemperature();
                    int currentTemperature = currentElement.getTemperature();
                    if (previousTemperature < currentTemperature) {
                        output.add(currentElement);
                    } else {
                        while (previousTemperature > currentTemperature && previousIndex > 0) {
                            previousIndex--;
                            previousElement = output.get(previousIndex);
                            previousTemperature = previousElement.getTemperature();
                        }
                        if (previousIndex != 0 || previousTemperature < currentTemperature) {
                            output.add(previousIndex + 1, currentElement);
                        } else output.add(previousIndex, currentElement);

                    }
                }
                elementCount++;
            }
        }
        return output;
    }

    @Override
    public DayTemperatureInfo getTemperature(int day, Month month) {
        int dayIndex = day - 1;
        int monthIndex = month.getValue() - 1;
        return dateTemperature.get(monthIndex).get(dayIndex);
    }
}