package impl.weather;

import api.weather.DayTemperatureInfo;
import api.weather.YearTemperatureStats;

import java.time.Month;
import java.util.*;

public class YearTemperatureStatsImpl implements YearTemperatureStats {
    private final Map<Month, MonthInfo> monthInfoMap;

    public YearTemperatureStatsImpl() {
        monthInfoMap = new EnumMap<>(Month.class);
    }

    @Override
    public void updateStats(DayTemperatureInfo info) {
        Month month = info.getMonth();
        monthInfoMap.putIfAbsent(month, new MonthInfo(month));
        MonthInfo monthInfo = monthInfoMap.get(month);
        monthInfo.updateMonthInfo(info);
    }

    @Override
    public Double getAverageTemperature(Month month) {
        if (monthInfoMap.containsKey(month)) {
            return monthInfoMap.get(month).averageTemperature;
        }
        return null;
    }

    @Override
    public Map<Month, Integer> getMaxTemperature() {
        Map<Month, Integer> output = new EnumMap<>(Month.class);
        Collection<MonthInfo> knownMonths = monthInfoMap.values();
        for (MonthInfo eachMonth : knownMonths) {
            output.put(eachMonth.month, eachMonth.maxTemperature);
        }
        return output;
    }

    @Override
    public List<DayTemperatureInfo> getSortedTemperature(Month month) {
        if (monthInfoMap.containsKey(month)) {
            return monthInfoMap.get(month).getSortedTemperature();
        }
        return new ArrayList<>();
    }

    @Override
    public DayTemperatureInfo getTemperature(int day, Month month) {
        if (monthInfoMap.containsKey(month)) {
            return monthInfoMap.get(month).getTemperature(day);
        }
        return null;
    }

    private static class MonthInfo {
        private final Month month;
        private final Map<Integer, DayTemperatureInfo> temperatures;
        private Double averageTemperature;
        private Integer maxTemperature;
        private int knownDays;

        MonthInfo(Month thisMonth) {
            month = thisMonth;
            temperatures = new LinkedHashMap<>(month.length(false));
            knownDays = 0;
        }

        private void updateMonthInfo(DayTemperatureInfo info) {
            Integer day = info.getDay();
            temperatures.put(day, info);
            int temperature = info.getTemperature();
            if (maxTemperature == null || maxTemperature < temperature) {
                maxTemperature = temperature;
            }
            if (averageTemperature == null) {
                averageTemperature = (double) temperature;
            } else {
                averageTemperature = (knownDays * averageTemperature + temperature) / (knownDays + 1);
            }
            knownDays++;
        }

        private DayTemperatureInfo getTemperature(int day) {
            if (temperatures.containsKey(day)) {
                return temperatures.get(day);
            }
            return null;
        }

        private List<DayTemperatureInfo> getSortedTemperature() {
            List<DayTemperatureInfo> output = new ArrayList<>(temperatures.values());
            output.sort(Comparator.comparingInt(DayTemperatureInfo::getTemperature));
            return output;
        }
    }
}
