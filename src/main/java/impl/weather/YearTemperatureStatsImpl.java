package impl.weather;

import api.weather.DayTemperatureInfo;
import api.weather.YearTemperatureStats;

import java.time.Month;
import java.util.*;

public class YearTemperatureStatsImpl implements YearTemperatureStats {
    private final Map<Month, MonthInfo> weatherMap;

    public YearTemperatureStatsImpl() {
        weatherMap = new EnumMap<>(Month.class);
    }

    @Override
    public void updateStats(DayTemperatureInfo info) {
        Month month = info.getMonth();

        weatherMap.putIfAbsent(month, new MonthInfo(month));
        MonthInfo monthInfo = weatherMap.get(month);
        monthInfo.updateMonthStats(info);
    }


    @Override
    public Double getAverageTemperature(Month month) {
        if (weatherMap.containsKey(month)) {
            return weatherMap.get(month).getAverageTemperature();
        }
        return null;
    }

    @Override
    public Map<Month, Integer> getMaxTemperature() {
        Map<Month, Integer> output = new EnumMap<>(Month.class);
        Collection<MonthInfo> months = weatherMap.values();

        for(MonthInfo month : months){
            output.put(month.month, month.getMaxTemperature());
        }

        return output;
    }

    @Override
    public List<DayTemperatureInfo> getSortedTemperature(Month month) {

        if (weatherMap.containsKey(month)) {
            return weatherMap.get(month).getSortedTemperature();
        }
        return new ArrayList<>();
    }

    @Override
    public DayTemperatureInfo getTemperature(int day, Month month) {
        if (weatherMap.containsKey(month)){
            return weatherMap.get(month).getTemperature(day);
        }

        return null;
    }


    private static class MonthInfo {
        private final Month month;
        private final Map<Integer, DayTemperatureInfo> temperatureMap;
        private Double averageTemperature;
        private Integer maxTemperature;
        private Integer quantityOfDays;

        public MonthInfo(Month month) {
            this.month = month;
            temperatureMap = new LinkedHashMap<>(month.length(false));
            averageTemperature = null;
            maxTemperature = null;
            quantityOfDays = 0;
        }

        public Double getAverageTemperature(){
            return averageTemperature;
        }

        public Integer getMaxTemperature(){
            return maxTemperature;
        }

        private void updateMonthStats(DayTemperatureInfo info){
            int day = info.getDay();
            int temperature = info.getTemperature();
            temperatureMap.put(day, info);
            quantityOfDays++;

            if(maxTemperature == null || maxTemperature < temperature){
                maxTemperature = temperature;
            }

            if(averageTemperature == null){
                averageTemperature = (double) temperature;
            } else {
                averageTemperature = (((quantityOfDays-1)*averageTemperature)+temperature)/quantityOfDays;
            }
        }

        private DayTemperatureInfo getTemperature(int day){
            if(temperatureMap.containsKey(day)){
                return temperatureMap.get(day);
            }
            return null;
        }

        private List<DayTemperatureInfo> getSortedTemperature(){
            List<DayTemperatureInfo> output = new ArrayList<>(temperatureMap.values());
            output.sort(Comparator.comparingInt(DayTemperatureInfo::getTemperature));
            return output;
        }

    }
}
