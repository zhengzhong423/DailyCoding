package BaseTrans.DecToBinary;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by zhonzhen on 7/6/16.
 */
public class Test {
    private Solution solution = new Solution();

    @org.junit.Test
    public void testAssertions(){

        assertEquals("101", solution.decToBianry(5));

        assertEquals("1101", solution.decToBianry(13));

        assertEquals("11", solution.decToBianry(3));
    }
}
