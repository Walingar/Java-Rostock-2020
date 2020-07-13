package impl.weather;

import api.weather.DayTemperatureInfo;
import api.weather.YearTemperatureStats;

import java.time.Month;
import java.util.*;

public class YearTemperatureStatsImpl implements YearTemperatureStats {
    private final Map<Month, Map<Integer, DayTemperatureInfo>> yearTemperatures;
    private final Map<Month, Integer> maxTemperatures;

    public YearTemperatureStatsImpl(){
        yearTemperatures = new EnumMap<>(Month.class);
        maxTemperatures = new EnumMap<>(Month.class);
    }

    @Override
    public void updateStats(DayTemperatureInfo info) {
        Month month = info.getMonth();
        Integer day = info.getDay();
        Integer temperature = info.getTemperature();
        if (yearTemperatures.containsKey(month)) {
            Map<Integer, DayTemperatureInfo> dayTemperatures = yearTemperatures.get(month);
            dayTemperatures.put(day, info);
            if (maxTemperatures.get(month) < temperature) {
                maxTemperatures.put(month, temperature);
            }
        } else {
            Map<Integer, DayTemperatureInfo> dayTemperatures = new HashMap<>(month.length(false));
            dayTemperatures.put(day, info);
            yearTemperatures.put(month, dayTemperatures);
            maxTemperatures.put(month, temperature);
        }
    }

    @Override
    public Double getAverageTemperature(Month month) {
        if (!yearTemperatures.containsKey(month)) {
            return null;
        }
        Map<Integer, DayTemperatureInfo> dayTemperatures = yearTemperatures.get(month);
        Collection<DayTemperatureInfo> knownDays = dayTemperatures.values();
        double sum = 0;
        int knownDaysCount = knownDays.size();
        for (DayTemperatureInfo day : knownDays) {
            sum += day.getTemperature();
        }
        return sum / knownDaysCount;
    }

    @Override
    public Map<Month, Integer> getMaxTemperature() {
        return maxTemperatures;
    }

    @Override
    public List<DayTemperatureInfo> getSortedTemperature(Month month) {
        if (yearTemperatures.containsKey(month)) {
            Map<Integer, DayTemperatureInfo> dayTemperatures = yearTemperatures.get(month);
            Collection<DayTemperatureInfo> knownDays = dayTemperatures.values();
            List<DayTemperatureInfo> output = new ArrayList<>(knownDays.size());
            // output.addAll(knownDays);
            int dayCount = 0;
            for (DayTemperatureInfo eachDay : knownDays) {
                if (dayCount == 0) {
                    output.add(eachDay);
                } else {
                    int previousDayIndex = dayCount - 1;
                    DayTemperatureInfo previousDay = output.get(previousDayIndex);
                    int previousTemperature = previousDay.getTemperature();
                    int currentTemperature = eachDay.getTemperature();
                    if (previousTemperature < currentTemperature) {
                        output.add(eachDay);
                    } else {
                        while (previousTemperature > currentTemperature && previousDayIndex > 0) {
                            previousDayIndex--;
                            previousDay = output.get(previousDayIndex);
                            previousTemperature = previousDay.getTemperature();
                        }
                        if (previousDayIndex != 0 || previousTemperature < currentTemperature) {
                            output.add(previousDayIndex + 1, eachDay);
                        } else {
                            output.add(previousDayIndex, eachDay);
                        }
                    }
                }
                 dayCount++;
            }
            // output.sort(Comparator.comparing(DayTemperatureInfo::getTemperature));
            return output;
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

}
