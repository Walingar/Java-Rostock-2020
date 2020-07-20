package impl.weather;

import api.weather.DayTemperatureInfo;
import api.weather.YearTemperatureStats;

import java.time.Month;
import java.util.*;

public class YearTemperatureStatsImpl implements YearTemperatureStats {
    private final Map<Month, Map<Integer, DayTemperatureInfo>> yearTemperatures;
    private final Map<Month, MonthInfo> monthInfo;

    public YearTemperatureStatsImpl() {
        yearTemperatures = new EnumMap<>(Month.class);
        monthInfo = new EnumMap<>(Month.class);
    }

    @Override
    public void updateStats(DayTemperatureInfo info) {
        Month month = info.getMonth();
        Integer day = info.getDay();
        Integer temperature = info.getTemperature();
        if (yearTemperatures.containsKey(month)) {
            Map<Integer, DayTemperatureInfo> dayTemperatures = yearTemperatures.get(month);
            dayTemperatures.put(day, info);
            MonthInfo currentMonthInfo = monthInfo.get(month);
            if (currentMonthInfo.maxTemperature < temperature) {
                currentMonthInfo.maxTemperature = temperature;
            }
            int knownDays = currentMonthInfo.knownDays;
            currentMonthInfo.averageTemperature = (knownDays * currentMonthInfo.averageTemperature + temperature) / (knownDays + 1);
            currentMonthInfo.knownDays++;
        } else {
            Map<Integer, DayTemperatureInfo> dayTemperatures = new HashMap<>(month.length(false));
            dayTemperatures.put(day, info);
            yearTemperatures.put(month, dayTemperatures);
            MonthInfo newMonthInfo = new MonthInfo(month);
            newMonthInfo.knownDays++;
            newMonthInfo.maxTemperature = temperature;
            newMonthInfo.averageTemperature = (double) temperature;
            monthInfo.put(month, newMonthInfo);
        }
    }

    @Override
    public Double getAverageTemperature(Month month) {
        if (monthInfo.containsKey(month)) {
            return monthInfo.get(month).averageTemperature;
        }
        return null;
    }

    @Override
    public Map<Month, Integer> getMaxTemperature() {
        Map<Month, Integer> output = new EnumMap<>(Month.class);
        Collection<MonthInfo> knownMonths = monthInfo.values();
        for (MonthInfo eachMonth : knownMonths) {
            output.put(eachMonth.month, eachMonth.maxTemperature);
        }
        return output;
    }

    @Override
    public List<DayTemperatureInfo> getSortedTemperature(Month month) {
        if (yearTemperatures.containsKey(month)) {
            Map<Integer, DayTemperatureInfo> dayTemperatures = yearTemperatures.get(month);
            Collection<DayTemperatureInfo> knownDays = dayTemperatures.values();
            ArrayList<DayTemperatureInfo> output = new ArrayList<>(knownDays.size());
            output.addAll(knownDays);
            return mergeSort(output);
        }
        return new ArrayList<>();
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

    public ArrayList<DayTemperatureInfo> mergeSort(ArrayList<DayTemperatureInfo> whole) {
        ArrayList<DayTemperatureInfo> left = new ArrayList<>();
        ArrayList<DayTemperatureInfo> right = new ArrayList<>();
        int center;
        if (whole.size() == 1) {
            return whole;
        } else {
            center = whole.size() / 2;
            for (int index = 0; index < center; index++) {
                left.add(whole.get(index));
            }
            for (int index = center; index < whole.size(); index++) {
                right.add(whole.get(index));
            }
            left = mergeSort(left);
            right = mergeSort(right);
            merge(left, right, whole);
        }
        return whole;
    }

    private void merge(ArrayList<DayTemperatureInfo> left, ArrayList<DayTemperatureInfo> right, ArrayList<DayTemperatureInfo> whole) {
        int leftIndex = 0;
        int rightIndex = 0;
        int wholeIndex = 0;
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex).getTemperature() < right.get(rightIndex).getTemperature()) {
                whole.set(wholeIndex, left.get(leftIndex));
                leftIndex++;
            } else {
                whole.set(wholeIndex, right.get(rightIndex));
                rightIndex++;
            }
            wholeIndex++;
        }
        ArrayList<DayTemperatureInfo> rest;
        int restIndex;
        if (leftIndex >= left.size()) {
            rest = right;
            restIndex = rightIndex;
        } else {
            rest = left;
            restIndex = leftIndex;
        }
        for (int i = restIndex; i < rest.size(); i++) {
            whole.set(wholeIndex, rest.get(i));
            wholeIndex++;
        }
    }

    static class MonthInfo {
        private Double averageTemperature;
        private Integer maxTemperature;
        private int knownDays;
        private final Month month;

        MonthInfo(Month thisMonth) {
            averageTemperature = null;
            maxTemperature = null;
            knownDays = 0;
            month = thisMonth;
        }
    }
}
