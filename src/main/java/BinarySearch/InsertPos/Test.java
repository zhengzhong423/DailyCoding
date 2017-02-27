package BinarySearch.InsertPos;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by zhonzhen on 9/7/16.
 */
public class Test {
    Solution sol = new Solution();

    @org.junit.Test
    public void findInsertPosTest(){
        assertEquals(2, sol.findInsertPos(new int[]{1, 2, 4}, 3));

        assertEquals(0, sol.findInsertPos(new int[]{1, 2, 5}, 0));

        assertEquals(3, sol.findInsertPos(new int[]{1, 2, 8}, 10));

        assertEquals(2, sol.findInsertPos(new int[]{1, 2, 4, 9}, 3));

        assertEquals(0, sol.findInsertPos(new int[]{1, 2, 5, 9}, 0));

        assertEquals(4, sol.findInsertPos(new int[]{1, 2, 8, 9}, 10));

    }

}
