package Review.RegularEXP;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhonzhen on 7/27/16.
 */
public class Solution {
    public static void main (String[] args) throws java.lang.Exception
    {
        Pattern p = Pattern.compile("([(]?\\d{3}[-|\\s|)]?\\d{3}[-|\\s]?\\d{4})");
        Matcher m = p.matcher("hello(213)509-7057goodboy789-232-3222very234 324 2345aa");
        while (m.find()) {
            System.out.println(m.group());
        }

    }
}
