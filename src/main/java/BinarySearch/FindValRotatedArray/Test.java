package BinarySearch.FindValRotatedArray;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by zhonzhen on 9/7/16.
 */
public class Test {
    Solution sol = new Solution();

    @org.junit.Test
    public void findValRotatedArrayTest() {
        assertEquals(3, sol.findValRotatedArray(new int[]{1,2,3,4,5,6}, 4));

        assertEquals(5, sol.findValRotatedArray(new int[]{5,6,7,2,3,4}, 4));

        assertEquals(9, sol.findValRotatedArray(new int[]{1,1,1,1,1,1,1,1,1,8}, 8));

        assertEquals(-1, sol.findValRotatedArray(new int[]{5,6,1,2,3,4,5,5,5,5}, 9));
    }
}
