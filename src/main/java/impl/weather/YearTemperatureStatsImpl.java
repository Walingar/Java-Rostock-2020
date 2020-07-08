package impl.weather;

import api.weather.DayTemperatureInfo;
import api.weather.YearTemperatureStats;

import java.time.Month;
import java.util.*;

public class YearTemperatureStatsImpl implements YearTemperatureStats {
    private final Map<Month, Map<Integer, DayTemperatureInfo>> yearTemperatures;
    private int monthCapacity;

    public YearTemperatureStatsImpl(){
        monthCapacity = 0;
        for (Month ignored : Month.values()) {
            monthCapacity++;
        }
        yearTemperatures = new HashMap<>(monthCapacity);
    }

    @Override
    public void updateStats(DayTemperatureInfo info) {
        Month month = info.getMonth();
        Integer day = info.getDay();
        if (yearTemperatures.containsKey(month)) {
            Map<Integer, DayTemperatureInfo> dayTemperatures = yearTemperatures.get(month);
            dayTemperatures.put(day, info);
        } else {
            Map<Integer, DayTemperatureInfo> dayTemperatures = new HashMap<>(month.length(false));
            dayTemperatures.put(day, info);
            yearTemperatures.put(month, dayTemperatures);
        }
    }

    @Override
    public Double getAverageTemperature(Month month) {
        if (yearTemperatures.containsKey(month)) {
            Map<Integer, DayTemperatureInfo> dayTemperatures = yearTemperatures.get(month);
            Collection<DayTemperatureInfo> knownDays = dayTemperatures.values();
            double sum = 0;
            int knownDaysCount = knownDays.size();
            for (DayTemperatureInfo day : knownDays) {
                sum += day.getTemperature();
            }
            return sum / knownDaysCount;
        } else {
            return null;
        }
    }

    @Override
    public Map<Month, Integer> getMaxTemperature() {
        Collection<Map<Integer, DayTemperatureInfo>> knownMonths = yearTemperatures.values();
        Map<Month, Integer> output = new HashMap<>(monthCapacity);
        for (Map<Integer, DayTemperatureInfo> eachMonth : knownMonths) {
            Collection<DayTemperatureInfo> knownDays = eachMonth.values();
            Integer eachMax = null;
            Month currentMonth = null;
            for (DayTemperatureInfo eachDay : knownDays) {
                int dayTemperature = eachDay.getTemperature();
                if (eachMax == null || eachMax < dayTemperature) {
                    eachMax = dayTemperature;
                    currentMonth = eachDay.getMonth();
                }
            }
            output.put(currentMonth, eachMax);
        }
        return output;
    }

    @Override
    public List<DayTemperatureInfo> getSortedTemperature(Month month) {
        List<DayTemperatureInfo> output = new ArrayList<>(month.length(false));
        if (yearTemperatures.containsKey(month)) {
            Map<Integer, DayTemperatureInfo> dayTemperatures = yearTemperatures.get(month);
            Collection<DayTemperatureInfo> knownDays = dayTemperatures.values();
            int dayCount = 0;
            for (DayTemperatureInfo eachDay : knownDays) {
                if (dayCount == 0) {
                    output.add(eachDay);
                } else {
                    int previousDayIndex = dayCount - 1;
                    DayTemperatureInfo previousDay = output.get(previousDayIndex);
                    int previousTemperature = previousDay.getTemperature();
                    int currentTemperature = eachDay.getTemperature();
                    if (previousTemperature < currentTemperature) output.add(eachDay);
                    else {
                        while (previousTemperature > currentTemperature && previousDayIndex > 0) {
                            previousDayIndex--;
                            previousDay = output.get(previousDayIndex);
                            previousTemperature = previousDay.getTemperature();
                        }
                        if (previousDayIndex != 0 || previousTemperature < currentTemperature) output.add(previousDayIndex + 1, eachDay);
                        else output.add(previousDayIndex, eachDay);
                    }
                }
                dayCount++;
            }
        }
        return output;
    }

    @Override
    public DayTemperatureInfo getTemperature(int day, Month month) {
        if (yearTemperatures.containsKey(month)) {
            Map<Integer, DayTemperatureInfo> dayTemperatures = yearTemperatures.get(month);
            if (dayTemperatures.containsKey(day)) {
                return yearTemperatures.get(month).get(day);
            }
        }
        return null;
    }
}
