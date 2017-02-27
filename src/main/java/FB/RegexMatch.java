package FB;

/**
 * Created by zhonzhen on 2/12/17.
 */
public class RegexMatch {
    public static void main(String[] args) {
        RegexMatch RM = new RegexMatch();
        System.out.println(RM.regexMatch("b", "a."));
        System.out.println(RM.regexMatch("aa", "a*"));
        System.out.println(RM.regexMatch("aab", "c*a*b"));
        System.out.println(RM.regexMatch("a", "b*a*c*"));
    }

    public boolean regexMatch(String s, String p) {
        return helper(s, p, 0, 0);
    }

    private boolean helper(String s, String p, int index1, int index2) {
        if (index1 < 0)
            return false;

        if(index1 == s.length() && index2 == p.length()) {
            return true;
        }
        if (index2 == p.length())
            return false;

        if (index1 == s.length()) {
            if (index2 + 1 < p.length() && p.charAt(index2 + 1) == '*') {
                return helper(s, p, index1, index2 + 2);
            } else {
                return false;
            }
        }

        if (p.charAt(index2) == '.') {
            return helper(s, p, index1 + 1, index2 + 1);
        }

        if (p.charAt(index2) == '*') {
            if (p.charAt(index2 - 1) == '.' || p.charAt(index2 - 1) == s.charAt(index1)) {
                return helper(s, p, index1 + 1, index2) ||
                        helper(s, p, index1 + 1, index2 + 1);
            } else {
                return false;
            }
        }


        if (s.charAt(index1) == p.charAt(index2)) {
            if (index2 + 1 < p.length() && p.charAt(index2 + 1) == '*')
                return helper(s, p, index1, index2 + 2) || helper(s, p, index1 + 1, index2 + 1)
                        || helper(s, p, index1, index2 + 1);
            return helper(s, p, index1 + 1, index2 + 1);
        }
        if (index2 + 1 < p.length() && p.charAt(index2 + 1) == '*')
            return helper(s, p, index1, index2 + 2) || helper(s, p, index1, index2 + 1);
        return false;
    }


}
