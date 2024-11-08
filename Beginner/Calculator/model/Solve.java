package model;

public class Solve {
    double firstNum;
    double secondNum;
    String sign;

    public Solve (double one, double two, String sign){
        firstNum = one;
        secondNum = two;
        this.sign = sign;
    }

    public double getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(double firstNum) {
        this.firstNum = firstNum;
    }

    public double getSecondNum() {
        return secondNum;
    }

    public void setSecondNum(double secondNum) {
        this.secondNum = secondNum;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    
}
