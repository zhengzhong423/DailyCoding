package MS;

/**
 * Created by zhonzhen on 2/20/17.
 */
public class LCS {
    public static void main(String[] args) {
        LCS lcs = new LCS();
        lcs.getLCS("ABCDGH" , "AEDFHR");
    }

    public String getLCS(String str1, String str2) {
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 1; i <= str1.length(); i++) {
            for(int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        System.out.println("Max Length is: " + dp[str1.length()][str2.length()]);

        int m = str1.length(), n = str2.length();
        StringBuilder sb = new StringBuilder();
        while(m > 0 && n > 0) {
            if (dp[m][n] == dp[m - 1][n]) {
                m--;
            } else if(dp[m][n] == dp[m][n - 1]) {
                n--;
            } else {
                if (dp[m][n] == dp[m - 1][n - 1] + 1) {
                    sb.insert(0, str1.charAt(m - 1));
                    m--;
                    n--;
                } else {
                    System.out.println("Error! dp[" + m + "][" + n + "] = " + dp[m][n]);
                }
            }
        }
        System.out.println(sb);
        return sb.toString();
    }
}
