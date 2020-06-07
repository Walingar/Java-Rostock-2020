package impl.expression;

import api.expression.ExpressionParser;
import api.expression.ParseException;

public class ExpressionParserImpl implements ExpressionParser {
    @Override
    public int parse(String expression) throws ParseException {

        if (expression == null){
            throw new IllegalArgumentException();
        }

        int result = 0;
        String originalExpression = expression;
        expression = expression.replaceAll("\\s+", ""); // remove whitespaces

        int stringLength = expression.length();
        String parsedInteger = "";
        String parsedOperator = "";

        for (int stringIndex = 0; stringIndex < stringLength; stringIndex++) {

            char currentElement = expression.charAt(stringIndex);

            if (Character.isLetter(currentElement)) {
                throw new ParseException(originalExpression);
            }

            if (Character.isDigit(currentElement)) {
                parsedInteger += currentElement;
            }

            if (!Character.isDigit(currentElement) || stringIndex == stringLength - 1) {
                if (stringIndex == 0 && currentElement == '-'){
                    parsedOperator = ""+currentElement;
                } else {
                    int parsedInt = Integer.parseInt(parsedInteger);
                    if (parsedOperator.equals("")) {
                        result = parsedInt;
                    } else {
                        if (parsedOperator.equals("+")) {
                            result += parsedInt;
                        } else {
                            result -= parsedInt;
                        }
                    }
                    parsedInteger = "";
                    parsedOperator = "" + currentElement;
                }
            }
        }

        return result;

    }
}
