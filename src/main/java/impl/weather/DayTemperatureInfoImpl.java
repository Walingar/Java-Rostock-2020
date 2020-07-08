package impl.weather;

import api.weather.DayTemperatureInfo;

import java.time.Month;

public class DayTemperatureInfoImpl implements DayTemperatureInfo {
    private Month month;
    private int day;
    private int temperature;

    public DayTemperatureInfoImpl(int dayValue, Month monthValue, int temperatureValue) {
        day = dayValue;
        month = monthValue;
        temperature = temperatureValue;
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
