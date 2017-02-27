package Yelp;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhonzhen on 2/6/17.
 */
public class listAnagrams {

    List<String> ret = new LinkedList<String>();

    public static void main(String[] args) {
        listAnagrams la = new listAnagrams();
        List<String> list = new LinkedList<String>();

        la.getAnagrams("abcd", 0, list);
        System.out.println(list);
    }

    private void getAnagrams(String str, int index, List<String> l) {
        if (index == str.length()) {
            l.add(str);
            return;
        }
        for(int i = index; i < str.length(); i++) {
            String tmpStr = swap(str, i, index);
            getAnagrams(tmpStr, index + 1, l);
        }
    }

    private String swap(String str, int i, int j) {
        char[] chs = str.toCharArray();
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
        return new String(chs);
    }
}
