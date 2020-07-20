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
        StringBuilder parsedIntegerBuilder = new StringBuilder();

        for (char currentChar : expression.toCharArray()) {
            if (!Character.isWhitespace(currentChar)) {
                if (Character.isDigit(currentChar)) {
                    parsedIntegerBuilder.append(currentChar);
                } else if (currentChar == '-' || currentChar == '+') {
                    if (parsedIntegerBuilder.length() != 0) {
                        result = calculateResult(parsedIntegerBuilder, result);
                        parsedIntegerBuilder.setLength(0);
                    }
                    parsedIntegerBuilder.append(currentChar);
                } else {
                    throw new ParseException("Faced unexpected char");
                }
            }
        }
        return calculateResult(parsedIntegerBuilder, result);
    }

    private int calculateResult(StringBuilder parsedIntegerBuilder, int result) throws ParseException {
        try {
            String parsedIntegerString = parsedIntegerBuilder.toString();
            int parsedInteger = Integer.parseInt(parsedIntegerString);
            return Math.addExact(result, parsedInteger);
        } catch (NumberFormatException numberFormatException) {
            throw new ParseException("Parsed value out of range for int");
        }
    }
}

