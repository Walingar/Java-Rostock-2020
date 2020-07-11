package impl.weather;

import api.weather.DayTemperatureInfoParser;

public class DayTemperatureInfoParserFactory {
    public static DayTemperatureInfoParser getInstance() {

        return new impl.weather.DayTemperatureInfoParser();
    }
}