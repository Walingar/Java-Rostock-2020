package impl.expression;

import api.expression.ExpressionParser;
import api.expression.ParseException;

import java.util.ArrayList;
import java.util.List;

public class ExpressionParserImpl implements ExpressionParser {
    @Override
    public int parse(String expression) throws ParseException {

        if (expression == null) {
            throw new IllegalArgumentException("expression is null");
        }
        int output = 0;
        StringBuilder expressionParseBuilder = new StringBuilder();


        for (char currentChar : expression.toCharArray()) {
            if (!Character.isWhitespace(currentChar)) {
                if (Character.isDigit(currentChar)) {
                    expressionParseBuilder.append(currentChar);
                } else {
                    if (currentChar == '-' || currentChar == '+') {
                        if (expressionParseBuilder.length() != 0) {
                            output = calculate(expressionParseBuilder, output);
                            expressionParseBuilder.setLength(0);
                        }
                        if (currentChar == '-') {
                            expressionParseBuilder.append(currentChar);
                        }
                    } else {
                        throw new ParseException("Unknown char in expression:" + currentChar);
                    }
                }
            }
        }
        return calculate(expressionParseBuilder, output);
    }

    private int calculate(StringBuilder expressionParseBuilder, int output) throws ParseException {
        try {
            String expressionParseString = expressionParseBuilder.toString();
            int expressionInteger = Integer.parseInt(expressionParseString);
            return Math.addExact(expressionInteger, output);
        } catch (NumberFormatException numberFormatException) {
            throw new ParseException("Expression is out of Integer-Range");
        }

    }


}

