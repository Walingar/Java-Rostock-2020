package impl.weather;

import api.weather.DayTemperatureInfo;
import api.weather.DayTemperatureInfoParser;

import java.time.Month;

public class DayTemperatureInfoParserFactory implements DayTemperatureInfoParser {

    public static DayTemperatureInfoParser getInstance() {
        return new DayTemperatureInfoParserFactory();
    }

    @Override
    public DayTemperatureInfo parse(String rawData) {

        int stringLength = rawData.length();
        String parsedDay = "";
        String parsedMonth = "";
        String parsedTemperature = "";
        String parsedSeparator = "";
        String parsedOperator = "";

        for (int stringIndex = 0; stringIndex < stringLength; stringIndex++) {
            char currentElement = rawData.charAt(stringIndex);
            if (Character.isWhitespace(currentElement) || currentElement == '.') {
                parsedSeparator += currentElement;
            } else {
                if (Character.isDigit(currentElement) && (parsedSeparator.length() == 0)) {
                    parsedDay += currentElement;
                }
                if (Character.isDigit(currentElement) && (parsedSeparator.length() == 1)) {
                    parsedMonth += currentElement;
                }
                if (Character.isDigit(currentElement) && (parsedSeparator.length() == 2)) {
                    parsedTemperature += currentElement;
                }
                if (currentElement == '-' && (parsedSeparator.length() == 2)) {
                    parsedOperator += currentElement;
                }
            }
        }

        int parsedDayInt = Integer.parseInt(parsedDay);
        int parsedMonthInt = Integer.parseInt(parsedMonth);
        Month month = Month.of(parsedMonthInt);
        int parsedTemperatureInt = Integer.parseInt(parsedTemperature);
        if (parsedOperator.length() != 0) {
            parsedTemperatureInt *= -1;
        }
        return null;
    }
}