package Yelp;

/**
 * Created by zhonzhen on 2/3/17.
 */
public class StrCompress {
    public static void main(String[] args) {
        StrCompress sc = new StrCompress();
        System.out.println(sc.compress("AAABBBababbb"));
    }


    public String compress(String str) {
        if (str == null)
            return null;
        if (str.length() == 0)
            return "";

        char[] chs = str.toCharArray();
        char preCh = chs[0];
        int counter = 1;
        int i = 1;
        String ret = "";

        while(i < chs.length) {
            if (chs[i] == preCh) {
                counter ++;
            } else {
                ret += counter + ("" + preCh);
                counter = 1;
                preCh = chs[i];
            }
            i++;
        }

        ret += counter + ("" + preCh);
        return ret;
    }
}
