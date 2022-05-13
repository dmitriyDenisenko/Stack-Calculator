import java.util.Scanner;
import java.util.Stack;

public class Calculator {
    static Stack<Integer> calculatorData = new Stack<>();
    static boolean flag = true;
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        printMenu();
        while(true){
            System.out.print("Input: ");
            String value = input.nextLine();
            if(value.contains("0b") && isBinary(value) && value.indexOf("b") == 1 && flag){
                calculatorData.push(Integer.parseInt(value.substring(2), 2));
            } else if(value.indexOf("0") == 0 && isOctal(value) && flag){
                calculatorData.push(Integer.parseInt(value.substring(1), 8));
            } else if(value.contains("0x") && isHexadecimal(value) && value.indexOf("x") == 1 && flag){
                calculatorData.push(Integer.parseInt(value.substring(2), 16));
            } else if(isDecimal(value) && flag){
                calculatorData.push(Integer.parseInt(value, 10));
            } else if(isOperand(value)){
                workWightOperators(value);
                flag = false;
            } else if(value.equals("=")){
                System.out.println(calculatorData.pop());
                return;
            } else {
                System.out.println("Invalid value entered");
            }
        }
    }

    public static boolean isOperand(String value){
        return value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/");
    }

    public static void printMenu(){
        System.out.println("Writing a binary number - 0bYourNumber");
        System.out.println("Writing an octal number - 0YourNumber");
        System.out.println("Writing an decimal number - YourNumber");
        System.out.println("Writing a hexadecimal number - 0xYourNumber");
        System.out.println("Entering numbers occurs before the appearance of operands");
    }

    public static boolean isBinary(String numb){
        try {
            Integer.parseInt(numb.substring(numb.indexOf("b") + 1), 2);
            return true;
        } catch (NumberFormatException ex){
            return false;
        }
    }

    public static boolean isOctal(String numb){
        try {
            Integer.parseInt(numb.substring(numb.indexOf("0") + 1), 8);
            return true;
        } catch (NumberFormatException ex){
            return false;
        }
    }

    public static boolean isHexadecimal(String numb){
        try {
            Integer.parseInt(numb.substring(numb.indexOf("x") + 1), 16);
            return true;
        } catch (NumberFormatException ex){
            return false;
        }
    }

    public static boolean isDecimal(String numb){
        try {
            Integer.parseInt(numb,10);
            return true;
        } catch (NumberFormatException ex){
            return false;
        }
    }

    public static void workWightOperators(String operator){
        int count;
        switch (operator){
            case "+":
                count = calculatorData.stream().mapToInt((s) -> s).sum();
                if(!flag){
                    calculatorData.pop();
                }
                calculatorData.push(count);
                System.out.println(count);

                break;
            case "-":
                count = calculatorData.stream().mapToInt((s) -> s).sum();
                int lastNumb = calculatorData.peek();
                if(!flag){
                    calculatorData.pop();
                }
                calculatorData.push(lastNumb - (count - lastNumb));
                System.out.println(lastNumb - (count - lastNumb));
                break;
            case "*":
                int mult =  calculatorData.stream().mapToInt(a -> a).reduce(1, (a, b) -> a * b);
                if(!flag){
                    calculatorData.pop();
                }
                calculatorData.push(mult);
                System.out.println(mult);
                break;
            case "/":
                int numb = calculatorData.pop();
                int del = calculatorData.stream().mapToInt(a -> a).reduce(1,(a,b) -> numb / b);
                if(!flag){
                    calculatorData.pop();
                }
                calculatorData.push(del);
                System.out.println(del);
                break;
        }
    }
}
