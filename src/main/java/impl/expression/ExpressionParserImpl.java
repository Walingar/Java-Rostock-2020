package impl.expression;

import api.expression.ExpressionParser;
import api.expression.ParseException;

import java.util.ArrayList;
import java.util.List;

public class ExpressionParserImpl implements ExpressionParser {
    @Override
    public int parse(String expression) throws ParseException {
        int output = 0;

        if (expression == null) {
             throw new IllegalArgumentException("expression is null");
        }
        else{
            expression.replaceAll("\\s+",""); //removes all whitespaces in expression
            int expressionLenght = expression.length();
            List<Integer> numberList = new ArrayList<Integer>();

            for(int characterIndex=0; characterIndex<expressionLenght; characterIndex++){
                char tempChar= expression.charAt(characterIndex);

                if ((tempChar >= 'a' && tempChar <= 'z') || (tempChar >= 'A' && tempChar <= 'Z')){
                    throw new ParseException("expression contains letters");
                }
                if (tempChar >= '0' && tempChar <= '9'){
                    int number = tempChar;
                    numberList.add(number);

                }
                if (tempChar == '+'){
                    if(numberList.isEmpty()==true){

                    }
                }
                if (tempChar == '-') {

                }
            }





        }

        return output;
    }
}
