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
        } else {
            expression = expression.replaceAll("\\s+", ""); //removes all whitespaces in expression
            int expressionLength = expression.length();
            String currentInt = "";
            String currentOp = "";

            int output = 0;

            for (int characterIndex = 0; characterIndex < expressionLength; characterIndex++) {
                char tempElement = expression.charAt(characterIndex);

                // Case: char is letter
                if (Character.isLetter(tempElement)) {
                    throw new ParseException("expression contains letters");
                }

                // Case: char is digit
                if (Character.isDigit(tempElement)) {
                    currentInt += tempElement;
                }

                // Case: char is +/- OR end of string
                if (!Character.isDigit(tempElement) || characterIndex == expressionLength - 1) {

                    //Case: - at the start of the expression
                    if (tempElement == '-' && characterIndex == 0) {
                        currentOp = "" + tempElement;
                    } else {
                        //Case: currentInt is out of Int-Range
                        long check = Long.parseLong(currentInt);
                        if (check > Integer.MAX_VALUE) {
                            throw new ParseException("parsed number is out of Int-Range");
                        }

                        int parsedInt = Integer.parseInt(currentInt);
                        if (currentOp == "") {
                            output = parsedInt;
                        } else {
                            if (currentOp.equals("+")) {
                                long checkOutput = output + parsedInt;
                                if (checkOutput >= Integer.MAX_VALUE || checkOutput <= Integer.MIN_VALUE) {
                                    throw new ArithmeticException("output out of Range");
                                }
                                output += parsedInt;
                            } else if (currentOp.equals("-")) {
                                long checkOutput = output - parsedInt;
                                if (checkOutput >= Integer.MAX_VALUE || checkOutput <= Integer.MIN_VALUE) {
                                    throw new ArithmeticException("output out of Range");
                                }
                                output -= parsedInt;
                            }
                        }
                        currentInt = "";
                        currentOp = "" + tempElement;
                    }
                }
            }

            return output;
        }
    }


}