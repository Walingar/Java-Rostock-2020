package impl.weather;

import api.weather.DayTemperatureInfo;

import java.time.Month;

public class DayTemperatureInfoParser implements api.weather.DayTemperatureInfoParser {
    @Override
    public DayTemperatureInfo parse(String rawData) {
        int stringLength = rawData.length();
        int nonLetterCounter = 0; // MAX = 3
        boolean negativeTemperature = false;

        StringBuilder dayString = new StringBuilder();
        StringBuilder monthString = new StringBuilder();
        StringBuilder temperatureString = new StringBuilder();

        for(int stringIterator = 0; stringIterator < stringLength; stringIterator++){
            char currentElement = rawData.charAt(stringIterator);

            if(Character.isWhitespace(currentElement) || currentElement == '.' || currentElement =='-'){
                nonLetterCounter++;

            } else{
                if(nonLetterCounter == 0){
                    dayString.append(currentElement);
                }
                if(nonLetterCounter == 1){
                    monthString.append(currentElement);
                }
                if(nonLetterCounter == 2){
                    temperatureString.append(currentElement);
                }
                if (nonLetterCounter == 3)
                    negativeTemperature = true;
                    temperatureString.append(currentElement);
                }
            }

        // DAY
        int day = Integer.parseInt(dayString.toString());

        // MONTH
        int monthInteger = Integer.parseInt(monthString.toString());
        Month month = Month.of(monthInteger);

        //TEMPERATURE
        int temperature = Integer.parseInt(temperatureString.toString());
        if(negativeTemperature) temperature = temperature * (-1);

        return new DayTemperatureInfoImpl(day,month,temperature);
    }
}
