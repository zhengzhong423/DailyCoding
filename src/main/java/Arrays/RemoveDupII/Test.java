package Arrays.RemoveDupII;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by zhonzhen on 7/6/16.
 */
public class Test {
    private Solution solution = new Solution();

    @org.junit.Test
    public void testAssertions(){

        assertEquals(2, solution.removeDuplicates(new int[]{1, 2}));

        assertEquals(5, solution.removeDuplicates(new int[]{1, 2, 2, 3, 3, 3}));

        assertEquals(4, solution.removeDuplicates(new int[]{1, 2, 2, 2, 3}));

        assertEquals(2, solution.removeDuplicates(new int[]{3, 3, 3}));


    }
}
