package api.weather;

import impl.weather.DayTemperatureInfoImpl;

import java.time.Month;
import java.util.List;
import java.util.Map;

public interface YearTemperatureStats {
    void updateStats(DayTemperatureInfoImpl info);

    Double getAverageTemperature(Month month);

    Map<Month, Integer> getMaxTemperature();

    List<DayTemperatureInfoImpl> getSortedTemperature(Month month);

    DayTemperatureInfoImpl getTemperature(int day, Month month);
}