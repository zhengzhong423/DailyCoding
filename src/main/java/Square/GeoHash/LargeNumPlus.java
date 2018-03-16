package Square.GeoHash;

import java.util.ArrayList;
import java.util.List;

public class LargeNumPlus {
    public static void main(String[] args) {
        LargeNumPlus lnp = new LargeNumPlus();
        System.out.println(lnp.times("100", "9999999"));
    }

    public String times(String str1, String str2) {
        if (str1.length() > str2.length()) {
            return plus(str2, str1);
        }
        StringBuilder sb1 = new StringBuilder(str1).reverse();
        StringBuilder sb2 = new StringBuilder(str2).reverse();

        int carry = 0, i = 0;

        List<StringBuilder> rsList = new ArrayList<>();
        for (; i < sb1.length(); i++) {
            Integer num1 = sb1.charAt(i) - '0';
            StringBuilder tmp = new StringBuilder();
            for (int j = 0; j < sb2.length(); j++) {
                Integer num2 = sb2.charAt(j) - '0';
                int sum = num1 * num2 + carry;
                tmp.append(sum % 10);
                carry = sum / 10;
            }
            if (carry > 0) {
                tmp.append(carry);
            }
            rsList.add(tmp);
        }

        i = 0;
        String rs = "";
        for (; i < rsList.size(); i++) {
            StringBuilder addOn = rsList.get(i).reverse();
            for (int  j = 0; j < i; j++) {
                addOn.append(0);
            }
            String normalizedStr = addOn.toString();
            rs = plus(rs, normalizedStr);
        }

        return rs;
    }

    public String plus (String str1, String str2) {
        if (str1.length() > str2.length()) {
            return plus(str2, str1);
        }
        StringBuilder sb1 = new StringBuilder(str1).reverse();
        StringBuilder sb2 = new StringBuilder(str2).reverse();

        StringBuilder result = new StringBuilder();
        int carry = 0, i = 0;
        for (; i < sb2.length(); i++) {
            Integer num1 = i >= sb1.length() ? 0 : sb1.charAt(i) - '0';
            Integer num2 = sb2.charAt(i) - '0';

            int sum = num1 + num2 + carry;
            carry = sum >= 10 ? 1 : 0;

            result.append(sum % 10);
        }

        if (carry == 1) {
            result.append(carry);
        }

        return result.reverse().toString();
    }
}
