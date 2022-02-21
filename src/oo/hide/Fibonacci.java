package oo.hide;

public class Fibonacci {
    private int nMinus1 = 1;
    private int nMinus2 = 0;

    public Integer nextValue() {
        int result = nMinus2;
        int sum = nMinus1 + nMinus2;
        nMinus2 = nMinus1;
        nMinus1 = sum;
        return result;
    }

}
