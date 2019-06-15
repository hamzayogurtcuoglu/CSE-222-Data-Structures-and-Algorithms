import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        Stack<variableClass> variableStack = new Stack<variableClass>();
        variableClass[] variableArray = null;
        int numberOfVariable = 0;
        BufferedReader reader;
        int k = 0;


        try {
            reader = new BufferedReader(new FileReader(
                    "src/test_file_part2"));
            String line = reader.readLine();

            while (line != null) {
                StringTokenizer abc = new StringTokenizer(line, "=", true);
                int i = 0;
                variableClass variable = null;
                if (line.contains("=")) {
                    variable = new variableClass();

                }
                while (line.contains("=") && abc.hasMoreTokens()) {
                    if (i == 0) {
                        String temp = abc.nextToken();
                        temp = temp.replaceAll(" ", "");
                        variable.setName(temp);
                    }
                    if (i == 1) {
                        abc.nextToken();
                    }

                    if (i == 2) {
                        numberOfVariable++;
                        float temp = Float.parseFloat(abc.nextToken());
                        variable.setValue(temp);
                        variableStack.push(variable);

                    }
                    i++;
                }

                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (k == 0) {
            variableArray = new variableClass[numberOfVariable];
            int l = 0;
            while (numberOfVariable != l) {
                variableArray[l] = variableStack.pop();
                l++;
                k++;
            }
        }

        String[] infixStringArray;
        int infixNumber = 0;

        try {
            reader = new BufferedReader(new FileReader(
                    "src/test_file_part2"));
            String line = reader.readLine();

            while (line != null) {
                StringTokenizer abc2 = new StringTokenizer(line, "(+*/-)", true);
                while (((line.contains("(") || (line.contains("+"))
                        || (line.contains("*")) || (line.contains("/"))
                        || (line.contains("-")) || (line.contains(")"))) && abc2.hasMoreTokens())) {
                    try {

                        String a = abc2.nextToken();

                        a = a.replaceAll(" ", "");
                        a = a.replaceAll("\n", "");
                        char first32 = a.charAt(0);

                        if (!a.contains("\n") && !a.contains(" ")) {
                            infixNumber++;
                        }
                    } catch (Exception e) {

                    }
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        infixStringArray = new String[infixNumber];

        infixNumber = 0;

        //all state infix putting in array
        try {
            reader = new BufferedReader(new FileReader(
                    "src/test_file_part2"));
            String line = reader.readLine();

            while (line != null) {
                StringTokenizer abc2 = new StringTokenizer(line, "(+*/-)", true);
                while (((line.contains("(") || (line.contains("+"))
                        || (line.contains("*")) || (line.contains("/"))
                        || (line.contains("-")) || (line.contains(")"))) && abc2.hasMoreTokens())) {
                    try {

                        String a = abc2.nextToken();

                        a = a.replaceAll(" ", "");
                        a = a.replaceAll("\n", "");
                        char first32 = a.charAt(0);

                        if (!a.contains("\n") && !a.contains(" ")) {
                            infixStringArray[infixNumber] = a;
                            infixNumber++;
                        }
                    } catch (Exception e) {
                    }
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < infixStringArray.length; i++)
            for (int j = 0; j < variableArray.length; j++) {
                if (infixStringArray[i].contains(variableArray[j].getName())) {
                    infixStringArray[i] = Float.toString(variableArray[j].getValue());
                }
            }

        String [] infix = trigonometricControl(infixStringArray);

        String [] postfix = infixToPostfix(infix);


        float Result = valueOFPostfix(postfix);
        System.out.println("\n\nResult : " + Result + "\n");

    }

    static float valueOFPostfix(String[] postFixStringArray){
        Stack<String> stack = new Stack<String>();


        for (int i = 0;postFixStringArray.length>i;i++){

            if (postFixStringArray[i] == null){
            }else if (postFixStringArray[i].length() == 1 &&(postFixStringArray[i].contains("+")||postFixStringArray[i].contains("-")||
                    postFixStringArray[i].contains("*")||postFixStringArray[i].contains("/"))) {
                String equation = "";
                String firstOperand = stack.pop();
                String secondOperand = stack.pop();
                float result = 0;
                if (postFixStringArray[i].contains("-"))
                    result = Float.parseFloat(secondOperand) -  Float.parseFloat(firstOperand);
                else {
                    switch ((postFixStringArray[i].charAt(0))) {
                        case '*':
                            result = Float.parseFloat(firstOperand) * Float.parseFloat(secondOperand);
                            break;
                        case '-':
                            result = Float.parseFloat(firstOperand) - Float.parseFloat(secondOperand);
                            break;
                        case '+':
                            result = Float.parseFloat(firstOperand) + Float.parseFloat(secondOperand);
                            break;
                        case '/':
                            result = Float.parseFloat(firstOperand) / Float.parseFloat(secondOperand);
                            break;
                    }
                }

                stack.push(Float.toString(result));

            }else{
                stack.push(postFixStringArray[i]);
            }


        }
        return Float.parseFloat(stack.pop());
    }

    static String [] infixToPostfix(String[] infixStringArray) {


        Stack<String> stack = new Stack<String>();
        String [] postfixArray = new String[infixStringArray.length];
        int index = 0;

        for (int i = 0;i<infixStringArray.length;i++){
            if (infixStringArray[i].length()==1&& (infixStringArray[i].contains("*")||infixStringArray[i].contains("/")
                ||infixStringArray[i].contains("+")||infixStringArray[i].contains("-"))||infixStringArray[i].contains(")")) {

                if (infixStringArray[i-1].contains("(")) {
                    postfixArray[index] = infixStringArray[i] + infixStringArray[i + 1];
                    index++;
                    i++;
                }else if (!stack.empty()&&infixStringArray[i].contains(")")){

                    postfixArray[index] = stack.pop();
                    index++;
                }else if (!stack.empty()){
                    String operatorStack = stack.peek();
                    if (precedence(operatorStack.charAt(0)) >= precedence(infixStringArray[i].charAt(0)) ){
                        if (infixStringArray[i+2].charAt(0) == ')'){
                            postfixArray[index] = infixStringArray[i+1];
                            index++;
                            postfixArray[index] = infixStringArray[i];
                            index++;
                            i++;
                        }else {
                            postfixArray[index] = stack.pop();
                            stack.push(infixStringArray[i]);
                            index++;
                        }
                    }else if (precedence(operatorStack.charAt(0)) < precedence(infixStringArray[i].charAt(0)) ) {
                        stack.push(infixStringArray[i]);
                    }
                }else{
                    stack.push(infixStringArray[i]);
                }

            }else if (!(infixStringArray[i].contains("(")||infixStringArray[i].contains(")"))) {

                postfixArray[index] = infixStringArray[i];
                index++;
            }
        }
        while (!stack.empty()){
            postfixArray[index] = stack.pop();
        }


        return postfixArray;
    }

    static int precedence(char operator){

        switch (operator){
            case '*':
                return 2;
            case '-':
                return 1;
            case '+':
                return 1;
            case '/':
                return 2;
        }
        return 0;
    }
    static String [] trigonometricControl(String [] infixStringArray){

        int index = 0;
        int i = 0;
        for (;i<infixStringArray.length;i++){
            if (infixStringArray[i].contains("sin")  || infixStringArray[i].contains( "cos") ||infixStringArray[i].contains( "abs") ){
                while(!infixStringArray[i].contains(")")){
                    i++;
                }
            }
            index++;
        }

        String [] NOtrigo = new String[index];

        int indexX = 0;
        for (int l = 0; l<infixStringArray.length;l++){

            if (infixStringArray[l].contains("sin")  || infixStringArray[l].contains( "cos") ||infixStringArray[l].contains( "abs") ){
                int triIndex = l;
                String calculateTri = "";
                boolean minusOrnot = true;
                while(!infixStringArray[l].contains(")")){
                    l++;
                    if ( (infixStringArray[l].contains(")")) ||( infixStringArray[l].contains("(")) ) {
                        if ((infixStringArray[l+1].contains("-")))
                        minusOrnot = false;
                    }else{
                        calculateTri += infixStringArray[l];
                    }
                }
                float resultTriIN;
                if (minusOrnot) {
                    resultTriIN = (float) compute(calculateTri);

                    switch (infixStringArray[triIndex]){

                        case "sin":
                            resultTriIN = sinTailor(resultTriIN);
                            break;
                        case "cos":
                            resultTriIN = cosTailor(resultTriIN);
                            break;
                        case "abs":
                            resultTriIN = absFunction(resultTriIN);
                            break;
                    }

                }else {

                    resultTriIN = Float.parseFloat(calculateTri);
                    switch (infixStringArray[triIndex]){

                        case "sin":
                            resultTriIN = sinTailor(resultTriIN);
                            break;
                        case "cos":
                            resultTriIN = cosTailor(resultTriIN);
                            break;
                        case "abs":
                            resultTriIN = absFunction(resultTriIN);
                            break;
                    }
                }
                NOtrigo[indexX] = Float.toString(resultTriIN);

            }else{
                NOtrigo[indexX] = infixStringArray[l];
            }
            indexX++;

        }

        return NOtrigo;
    }

    static double compute(String equation) {
        double result = 0.0;

        String deletingMinus = equation.replace("-", "+-");

        String[] pluses = deletingMinus.split("\\+");

        for (String mul : pluses) {

            String[] byMultipl = mul.split("\\*");

            double mulResult = 1.0;

            for (String operand : byMultipl) {
                if (operand.contains("/")) {

                    String[] division = operand.split("\\/");
                    double divident = Double.parseDouble(division[0]);

                    for (int i = 1; i < division.length; i++) {
                        divident /= Double.parseDouble(division[i]);
                    }

                    mulResult *= divident;

                } else {
                    mulResult *= Double.parseDouble(operand);
                }
            }
            result += mulResult;
        }
        return result;
    }


    public static float absFunction(float x){

        if (x<0){
            x = x*(-1);
        }
        return x;
    }

    static float sinTailor(float sinDegree) {

            double denom = 0;
            double numrad = sinDegree * (2 * (3.14) / 360);
            double sum = 0;

            int sign = 1;
            for (int counter = 0; counter < 9; counter++) {
                denom = counter * 2 + 1;

                double value = 1;
                for (int i = 0; i<denom;i++)
                    value *= numrad ;

                sum += sign * value / factorial(denom);
                sign *= -1;
            }
            return (float) sum;
    }
    public static double factorial(double num){
        if(num == 0) return 1;
        return num * factorial(num -1);
    }

    public static float cosTailor(float x)
    {
        x = (float) (x * ((3.14) / 180.0));

        double res = 1;
        double sign = 1,
                factorial = 1,
                pow = 1;
        for (int i = 1; i < 9; i++)
        {
            factorial = factorial * (2 * i - 1) * (2 * i);
            sign = sign * -1;
            pow = pow * x * x;
            res = res + sign * pow / factorial;
        }
        return (float) res;
    }
}