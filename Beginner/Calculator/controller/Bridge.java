package controller;

import java.util.Stack;
import model.Solve;

public class Bridge {
    Solve solve;

    private double sum(Solve value){
        double sum = value.getFirstNum() + value.getSecondNum();
        return sum;
    }

    private double multiply(Solve value){
        double sum = value.getFirstNum() * value.getSecondNum();
        return sum;
    }

    private double subtract(Solve value){
        double sum = value.getFirstNum() - value.getSecondNum();
        return sum;
    }

    private double divide(Solve value){
        double sum = value.getFirstNum() / value.getSecondNum();
        return sum;
    }

    public double stackers(String value){
        Stack<String> operations = new Stack<>();

        char[] numbers = value.toCharArray();
        String temp = "";

        for(int x = 0 ; x < numbers.length + 1; x++){
            if (x == numbers.length){
                operations.add(temp);
            } else if(numbers[x] == '+' || numbers[x] == '-' || numbers[x] == '*' || numbers[x] == '/'){
                operations.add(temp);
                temp = "";
                operations.add(String.valueOf(numbers[x]));
            } else {
                temp += numbers[x];  
            }
        }

        double finalVal = splitter(operations);

        return finalVal;
    }

    private double splitter(Stack<String> a){
        double sum = 0;
        while(a.size() != 1){
            Solve set = new Solve(Double.parseDouble(a.get(0)), Double.parseDouble(a.get(2)), a.get(1));
            sum = solver(set);
            for(int i = 0; i < 3; i++){
                a.remove(0);
            }
            a.insertElementAt(String.valueOf(sum), 0);
        }
        return sum;
    }

    private double solver(Solve value){
        double sum = 0;

        switch(value.getSign()){
            case "*" :
                sum = multiply(value);
                break;
            case "/" :
                sum = divide(value);
                break;
            case "+":
                sum = sum(value);
                break;
            case "-":
                sum = subtract(value);
                break;
        }

        return sum;
    }

}
