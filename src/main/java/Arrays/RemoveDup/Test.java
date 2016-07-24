package Arrays.RemoveDup;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by zhonzhen on 7/6/16.
 */
public class Test {

    private Solution solution = new Solution();

    @org.junit.Test
    public void testAssertions(){

        assertEquals(3, solution.removeDuplicates(new int[]{1, 2, 3}));

        assertEquals(3, solution.removeDuplicates(new int[]{1, 2, 2, 3}));

        assertEquals(1, solution.removeDuplicates(new int[]{3, 3, 3}));

        assertEquals(0, solution.removeDuplicates(new int[]{}));
    }
}
