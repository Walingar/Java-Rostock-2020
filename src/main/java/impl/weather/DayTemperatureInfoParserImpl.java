package impl.weather;

import api.weather.DayTemperatureInfo;
import api.weather.DayTemperatureInfoParser;

import java.time.Month;

public class DayTemperatureInfoParserImpl implements DayTemperatureInfoParser {
    @Override
    public DayTemperatureInfo parse(String expression) {

        StringBuilder parsedDayBuilder = new StringBuilder();
        StringBuilder parsedMonthBuilder = new StringBuilder();
        StringBuilder parsedTemperatureBuilder = new StringBuilder();

        int separatorCount = 0;

        for (char currentChar : expression.toCharArray()) {
            if (Character.isWhitespace(currentChar) || currentChar == '.') {
                separatorCount++;
            } else {
                if (separatorCount == 0) {
                    parsedDayBuilder.append(currentChar);
                }
                if (separatorCount == 1) {
                    parsedMonthBuilder.append(currentChar);
                }
                if (separatorCount == 2) {
                    parsedTemperatureBuilder.append(currentChar);
                }
            }
        }

        int parsedDay = Integer.parseInt(parsedDayBuilder.toString());
        int parsedMonth = Integer.parseInt(parsedMonthBuilder.toString());
        Month month = Month.of(parsedMonth);
        int parsedTemperature = Integer.parseInt(parsedTemperatureBuilder.toString());

        return new DayTemperatureInfoImpl(parsedDay, month, parsedTemperature);
    }
}
