package Paypal.splitTest;

import java.util.Arrays;

public class SplitLearning {
    public static void main(String[] args) {
        String s1 = "1/2+3/4-4/3+4/3";
        System.out.println(Arrays.toString(s1.split("\\+|-")));
        System.out.println(Arrays.toString(s1.split("\\d|/")));

        SplitLearning sl = new SplitLearning();
        System.out.println(sl.leastCommonMuliple(20, 28));
    }

    public Integer leastCommonMuliple (Integer num1, Integer num2) {
        return (num1 / GCD(num1, num2)) * (num2 / GCD(num1, num2)) * GCD(num1, num2);
    }

    private Integer GCD (Integer num1, Integer num2) {
        while(num1 % num2 != 0) {
            int tmp = num2;
            num2 = num1 % num2;
            num1 = tmp;
        }
        return num2;
    }
}

