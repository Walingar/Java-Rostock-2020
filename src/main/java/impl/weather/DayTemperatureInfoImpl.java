package impl.weather;

import org.jetbrains.annotations.NotNull;

import java.time.Month;
import java.util.Comparator;

public class DayTemperatureInfoImpl implements api.weather.DayTemperatureInfo, Comparable<DayTemperatureInfoImpl> {
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

    @Override
    public int compareTo(@NotNull DayTemperatureInfoImpl o) {
        if(this.getTemperature() > o.getTemperature()) return 1;
        if(this.getTemperature() < o.getTemperature()) return -1;
        else return 0;
    }
}
