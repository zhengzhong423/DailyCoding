package Locked.ShortestWordDistance;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by zhonzhen on 7/27/16.
 */
public class Test {
    Solution sol = new Solution();

    @org.junit.Test
    public void testCase0(){
        String[] strs = {"a", "a", "a", "c", "b", "b","a"};
        assertEquals(1, sol.getMinWordDis(strs, "a", "b"));
    }

    @org.junit.Test
    public void testCase1(){
        String[] strs = {"a", "a", "a", "c", "b", "b","d"};
        assertEquals(4, sol.getMinWordDis(strs, "a", "d"));
    }
}
