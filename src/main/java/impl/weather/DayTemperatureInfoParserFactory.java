package impl.weather;

import api.weather.DayTemperatureInfo;
import api.weather.DayTemperatureInfoParser;

import java.time.Month;

class DayTemperatureInfoFactory implements DayTemperatureInfo {
    private static int day;
    private static Month month;
    private static int temperature;

    public static DayTemperatureInfoFactory getInstance(int dayValue, Month monthValue, int temperatureValue) {
        day = dayValue;
        month = monthValue;
        temperature = temperatureValue;
        return new DayTemperatureInfoFactory();
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

public class DayTemperatureInfoParserFactory implements DayTemperatureInfoParser {

    public static DayTemperatureInfoParser getInstance() {
        return new DayTemperatureInfoParserFactory();
    }

    @Override
    public DayTemperatureInfo parse(String expression) {

        int stringLength = expression.length();
        String parsedDay = "";
        String parsedMonth = "";
        String parsedTemperature = "";
        int separatorCount = 0;
        Boolean negativeTemperature = false;

        for (int stringIndex = 0; stringIndex < stringLength; stringIndex++) {
            char currentElement = expression.charAt(stringIndex);
            if (Character.isWhitespace(currentElement) || currentElement == '.') {
                separatorCount++;
            } else {
                if (separatorCount == 0) parsedDay += currentElement;
                if (separatorCount == 1) parsedMonth += currentElement;
                if (separatorCount == 2) {
                    if (currentElement == '-') negativeTemperature = true;
                    else parsedTemperature += currentElement;
                }
            }
        }

        int parsedDayInt = Integer.parseInt(parsedDay);
        int parsedMonthInt = Integer.parseInt(parsedMonth);
        Month month = Month.of(parsedMonthInt);
        int parsedTemperatureInt = Integer.parseInt(parsedTemperature);
        if (negativeTemperature) {
            parsedTemperatureInt *= -1;
        }
        return DayTemperatureInfoFactory.getInstance(parsedDayInt, month, parsedTemperatureInt);
    }
}