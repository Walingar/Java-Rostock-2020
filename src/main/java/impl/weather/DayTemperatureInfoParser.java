package impl.weather;

import java.time.Month;

public class DayTemperatureInfoParser implements api.weather.DayTemperatureInfoParser {
    @Override
    public api.weather.DayTemperatureInfo parse(String rawData) {
        int nonLetterCounter = 0; // MAX = 2

        StringBuilder dayString = new StringBuilder();
        StringBuilder monthString = new StringBuilder();
        StringBuilder temperatureString = new StringBuilder();

        for (char currentChar : rawData.toCharArray()) {

            if (Character.isWhitespace(currentChar) || currentChar == '.') {
                nonLetterCounter++;

            } else {
                if (nonLetterCounter == 0) {
                    dayString.append(currentChar);
                }
                if (nonLetterCounter == 1) {
                    monthString.append(currentChar);
                }
                if (nonLetterCounter == 2) {
                    temperatureString.append(currentChar);
                }
            }

        }

        // DAY
        int day = Integer.parseInt(dayString.toString());

        // MONTH
        int monthInteger = Integer.parseInt(monthString.toString());
        Month month = Month.of(monthInteger);

        //TEMPERATURE
        int temperature = Integer.parseInt(temperatureString.toString());

        return new DayTemperatureInfoImpl(day, month, temperature);
    }
}
