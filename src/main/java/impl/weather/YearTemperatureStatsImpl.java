package impl.weather;

import api.weather.DayTemperatureInfo;
import api.weather.YearTemperatureStats;

import java.time.Month;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class YearTemperatureStatsImpl implements YearTemperatureStats {
    private List<List<DayTemperatureInfo>> yearTemperatures;

    public YearTemperatureStatsImpl(){
        yearTemperatures = new LinkedList<>();
        for (Month month : Month.values()) {
            List<DayTemperatureInfo> eachMonth = new LinkedList<>();
            for (int day = 0; day < month.length(false); day++) eachMonth.add(null);
            yearTemperatures.add(eachMonth);
        }
    }

    @Override
    public void updateStats(DayTemperatureInfo info) {
        int dayIndex = info.getDay() - 1;
        int monthIndex = info.getMonth().getValue() - 1;
        yearTemperatures.get(monthIndex).set(dayIndex, info);
    }

    @Override
    public Double getAverageTemperature(Month month) {
        int monthIndex = month.getValue() - 1;
        List<DayTemperatureInfo> affectedMonth = yearTemperatures.get(monthIndex);
        double sum = 0;
        int daysKnown = 0;
        int checkEmptyMonth = 0;
        for (DayTemperatureInfo day : affectedMonth) {
            if (day != null) {
                sum += day.getTemperature();
                daysKnown += 1;
            } else checkEmptyMonth += 1;
        }
        if (checkEmptyMonth == month.length(false)) return null;
        else return sum / daysKnown;
    }

    @Override
    public Map<Month, Integer> getMaxTemperature() {
        Map<Month, Integer> maxTemperatures = new HashMap<>();
        for (List<DayTemperatureInfo> eachMonth : yearTemperatures) {
            Month month = Month.of(yearTemperatures.indexOf(eachMonth) + 1);
            Integer eachMax = null;
            int checkEmptyMonth = 0;
            for (DayTemperatureInfo day : eachMonth) {
                if (day != null) {
                    int dayTemperature = day.getTemperature();
                    if (eachMax == null || eachMax < dayTemperature) eachMax = dayTemperature;
                } else checkEmptyMonth += 1;
            }
            if (checkEmptyMonth != month.length(false)) maxTemperatures.put(month, eachMax);
        }
        return maxTemperatures;
    }

    @Override
    public List<DayTemperatureInfo> getSortedTemperature(Month month) {
        List<DayTemperatureInfo> output = new LinkedList<>();
        List<DayTemperatureInfo> affectedMonth = yearTemperatures.get(month.getValue() - 1);
        int dayCount = 0;
        for (DayTemperatureInfo currentDay : affectedMonth) {
            if (currentDay != null) {
                if (dayCount == 0) output.add(currentDay);
                else {
                    int previousDayIndex = dayCount - 1;
                    DayTemperatureInfo previousDay = output.get(previousDayIndex);
                    int previousTemperature = previousDay.getTemperature();
                    int currentTemperature = currentDay.getTemperature();
                    if (previousTemperature < currentTemperature) output.add(currentDay);
                    else {
                        while (previousTemperature > currentTemperature && previousDayIndex > 0) {
                            previousDayIndex--;
                            previousDay = output.get(previousDayIndex);
                            previousTemperature = previousDay.getTemperature();
                        }
                        if (previousDayIndex != 0 || previousTemperature < currentTemperature) output.add(previousDayIndex + 1, currentDay);
                        else output.add(previousDayIndex, currentDay);
                    }
                }
                dayCount++;
            }
        }
        return output;
    }

    @Override
    public DayTemperatureInfo getTemperature(int day, Month month) {
        int dayIndex = day - 1;
        int monthIndex = month.getValue() - 1;
        return yearTemperatures.get(monthIndex).get(dayIndex);
    }
}
