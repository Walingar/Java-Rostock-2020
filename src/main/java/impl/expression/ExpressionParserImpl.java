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
        StringBuilder parsedIntegerBuilder = new StringBuilder();
        boolean isNegative = false;

        for (int stringIndex = 0; stringIndex < stringLength; stringIndex++) {
            char currentElement = expression.charAt(stringIndex);
            if (!Character.isWhitespace(currentElement)) {
                if (Character.isDigit(currentElement)) {
                    parsedIntegerBuilder.append(currentElement);
                } else if (currentElement == '-' || (currentElement == '+')) {
                    result = calculateResult(parsedIntegerBuilder, isNegative, result);
                    parsedIntegerBuilder.setLength(0);
                    isNegative = currentElement == '-';
                } else {
                    throw new ParseException("Faced unexpected char");
                }
            }
        }
        return calculateResult(parsedIntegerBuilder, isNegative, result);
    }

    private int calculateResult(StringBuilder parsedIntegerBuilder, boolean isNegative, int resultOld) throws ParseException{
        try {
            String parsedIntegerString = parsedIntegerBuilder.toString();
            int parsedInteger = 0;
            if (!parsedIntegerString.isEmpty()) {
                parsedInteger = Integer.parseInt(parsedIntegerString);
            }
            if (isNegative) {
                return safeSubtract(resultOld, parsedInteger);
            } else {
                return safeAdd(resultOld, parsedInteger);
            }
        } catch (NumberFormatException numberFormatException) {
            throw new ParseException("Parsed value out of range for int");
        }
    }
    private int safeAdd(int left, int right) {
        if (right > 0 ? left > Integer.MAX_VALUE - right
                : left < Integer.MIN_VALUE - right) {
            throw new ArithmeticException("Integer overflow");
        }
        return left + right;
    }

    private int safeSubtract(int left, int right) {
        if (right > 0 ? left < Integer.MIN_VALUE + right
                : left > Integer.MAX_VALUE + right) {
            throw new ArithmeticException("Integer overflow");
        }
        return left - right;
    }
}

