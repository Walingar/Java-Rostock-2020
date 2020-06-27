package api.weather;

public interface DayTemperatureInfoParser {
    DayTemperatureInfo parse(String rawData);
}