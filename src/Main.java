import java.util.*;

public class Main {
    private static final List<String> history = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Calculator!");
        System.out.println("Supported operations: +, -, *, /, %, power(base, exponent), sqrt(number), abs(number), round(number)");
        while (true) {
            System.out.print("Please enter your arithmetic expression: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("history")) {
                printHistory();
                continue;
            }
            try {
                double result = evaluateExpression(input);
                System.out.println("Result: " + result);
                history.add(input + " = " + result);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.print("Do you want to continue? (y/n): ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("n")) {
                break;
            }
        }

        System.out.println("Thank you for using the Calculator!");
        scanner.close();
    }
    private static double evaluateExpression(String input) throws Exception {
        input = input.replaceAll("\\s", "");
        while (input.contains("power(") || input.contains("sqrt(") || input.contains("abs(") || input.contains("round(")) {
            if (input.contains("power(")) {
                input = replaceFunction(input, "power");
            } else if (input.contains("sqrt(")) {
                input = replaceFunction(input, "sqrt");
            } else if (input.contains("abs(")) {
                input = replaceFunction(input, "abs");
            } else if (input.contains("round(")) {
                input = replaceFunction(input, "round");
            }
        }
        return evaluateArithmeticExpression(input);
    }
    private static String replaceFunction(String input, String functionName) throws Exception {
        int startIndex = input.indexOf(functionName + "(");
        int endIndex = findClosingBracket(input, startIndex + functionName.length());
        String functionCall = input.substring(startIndex, endIndex + 1);
        double result = evaluateFunction(functionCall);
        return input.replace(functionCall, String.valueOf(result));
    }

    private static int findClosingBracket(String input, int startIndex) {
        int bracketCount = 1;
        for (int i = startIndex + 1; i < input.length(); i++) {
            if (input.charAt(i) == '(') bracketCount++;
            if (input.charAt(i) == ')') bracketCount--;
            if (bracketCount == 0) return i;
        }
        return -1;
    }

    private static double evaluateFunction(String functionCall) throws Exception {
        if (functionCall.startsWith("power(")) {
            return powerFunction(functionCall);
        } else if (functionCall.startsWith("sqrt(")) {
            return sqrtFunction(functionCall);
        } else if (functionCall.startsWith("abs(")) {
            return absFunction(functionCall);
        } else if (functionCall.startsWith("round(")) {
            return roundFunction(functionCall);
        } else {
            throw new Exception("Unsupported function: " + functionCall);
        }
    }
    private static double powerFunction(String input) throws Exception {
        String[] parts = input.replace("power(", "").replace(")", "").split(",");
        if (parts.length != 2) throw new Exception("Invalid format for power function");
        return Math.pow(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
    }

    private static double sqrtFunction(String input) throws Exception {
        String num = input.replace("sqrt(", "").replace(")", "");
        double value = Double.parseDouble(num);
        if (value < 0) throw new Exception("Cannot calculate square root of a negative number");
        return Math.sqrt(value);
    }

    private static double absFunction(String input) throws Exception {
        String num = input.replace("abs(", "").replace(")", "");
        return Math.abs(Double.parseDouble(num));
    }

    private static double roundFunction(String input) throws Exception {
        String num = input.replace("round(", "").replace(")", "");
        return Math.round(Double.parseDouble(num));
    }

    private static double evaluateArithmeticExpression(String expression) throws Exception {

        expression = expression.replaceAll("\\s", "");
        if (expression.contains("/0")) {
            throw new Exception("Division by zero is not allowed");
        }
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (Character.isDigit(ch) || ch == '.') {
                StringBuilder numBuilder = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    numBuilder.append(expression.charAt(i));
                    i++;
                }
                i--;
                numbers.push(Double.parseDouble(numBuilder.toString()));
            }
            else if (ch == '(') {
                operators.push(ch);
            }
            else if (ch == ')') {
                while (operators.peek() != '(') {
                    numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
                }
                operators.pop();
            }
            else if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '%') {
                while (!operators.isEmpty() && hasPrecedence(ch, operators.peek())) {
                    numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
                }
                operators.push(ch);
            }
        }
        while (!operators.isEmpty()) {
            numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
        }

        return numbers.pop();
    }
    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') return false;
        return (op1 != '*' && op1 != '/' && op1 != '%') || (op2 != '+' && op2 != '-');
    }

    private static double applyOperation(char operator, double b, double a) throws Exception {
        switch (operator) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) throw new Exception("Division by zero is not allowed");
                return a / b;
            case '%': return a % b;
            default: throw new Exception("Unsupported operator: " + operator);
        }
    }

    private static void printHistory() {
        if (history.isEmpty()) {
            System.out.println("History is empty.");
        } else {
            System.out.println("Calculation History:");
            for (String record : history) {
                System.out.println(record);
            }
        }
    }
}