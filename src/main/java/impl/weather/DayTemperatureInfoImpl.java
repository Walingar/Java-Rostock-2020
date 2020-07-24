package impl.weather;

import api.weather.DayTemperatureInfo;
import org.jetbrains.annotations.NotNull;

import java.time.Month;
import java.util.Comparator;

public class DayTemperatureInfoImpl implements DayTemperatureInfo {
    private final int day;
    private final Month month;
    private final int temperature;

    public DayTemperatureInfoImpl(int day, Month month, int temperature){
        this.day = day;
        this.month = month;
        this.temperature = temperature;
    }
    @Override
    public int getDay() {
        return day;
    }

    @Override
    public Month getMonth() {
        return month;
    }

    @Override
    public int getTemperature() {
        return temperature;
    }

}