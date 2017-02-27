package Arrays.FindHole;

import static org.junit.Assert.assertEquals;

/**
 * Created by zhonzhen on 8/29/16.
 */
public class Test {
    private Solution solution = new Solution();

    @org.junit.Test
    public void testAssertion() {
        assertEquals(2, solution.findHole(new int[] {0,1,3,4}));

        assertEquals(5, solution.findHole(new int[] {3,4,1,0,2}));
    }
}
