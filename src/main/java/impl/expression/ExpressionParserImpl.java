package impl.expression;

import api.expression.ExpressionParser;
import api.expression.ParseException;

public class ExpressionParserImpl implements ExpressionParser {
    @Override
    public int parse(String expression) throws ParseException {

        if (expression == null) {
            throw new IllegalArgumentException("String is empty.");
        }

        int result = 0;
        int stringLength = expression.length();
        String parsedInteger = "";
        String parsedOperator = "";
        int firstRelevantCharacter = 0;

        for (int stringIndex = 0; stringIndex < stringLength; stringIndex++) {

            char currentElement = expression.charAt(stringIndex);

            if (!Character.isWhitespace(currentElement) || stringIndex == stringLength - 1) {

                if (Character.isLetter(currentElement)) {
                    throw new ParseException("Expression consists of letters.");
                }

                if (Character.isDigit(currentElement)) {
                    parsedInteger += currentElement;
                }

                if (!Character.isDigit(currentElement) || stringIndex == stringLength - 1) {
                    if (stringIndex == firstRelevantCharacter && currentElement == '-') {
                        parsedOperator = "" + currentElement;
                    } else {
                        long checkIntOrLong = Long.parseLong(parsedInteger);

                        if (checkIntOrLong > Integer.MAX_VALUE || checkIntOrLong < Integer.MIN_VALUE) {
                            throw new ParseException("Expression contains too high numbers for int.");
                        }
                        int parsedInt = Integer.parseInt(parsedInteger);

                        if (parsedOperator.equals("")) {
                            result = parsedInt;
                        } else {

                            if (parsedOperator.equals("+")) {
                                long checkIntRange = result + parsedInt;

                                if (checkIntRange >= Integer.MAX_VALUE || checkIntRange <= Integer.MIN_VALUE) {
                                    throw new ArithmeticException("Int value out of range after calculating.");
                                } else {
                                    result += parsedInt;
                                }
                            } else {
                                long rangeTestInt = result - parsedInt;

                                if (rangeTestInt <= Integer.MIN_VALUE) {
                                    throw new ArithmeticException("Int value out of range after calculating.");
                                } else {
                                    result -= parsedInt;
                                }
                            }
                        }
                        parsedInteger = "";
                        parsedOperator = "" + currentElement;
                    }
                }
            } else {
                firstRelevantCharacter += 1;
            }
        }
        return result;
    }
}
