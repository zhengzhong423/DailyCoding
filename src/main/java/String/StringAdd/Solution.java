package String.StringAdd;

/**
 * Created by zhonzhen on 10/17/16.
 */
public class Solution {
    public String strAdd(String s1, String s2) {
        StringBuilder sb1 = new StringBuilder(s1).reverse();
        StringBuilder sb2 = new StringBuilder(s2).reverse();
        StringBuilder ret_sb = new StringBuilder();
        int i = 0, j = 0, carry = 0;
        while(i < sb1.length() || j < sb2.length()) {
            int num1 = i >= sb1.length() ? 0 : sb1.charAt(i) - '0';
            int num2 = j >= sb2.length() ? 0 : sb2.charAt(j) - '0';
            ret_sb.append((num1 + num2 + carry) % 10);
            carry = (num1 + num2 + carry) >= 10 ? 1 : 0;
            i++;
            j++;
        }
        if (carry == 1)
            ret_sb.append(1);
        return ret_sb.reverse().toString();
    }

}
