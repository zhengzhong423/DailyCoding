package Amazon.Celebrity;

import static org.junit.Assert.assertEquals;

/**
 * Created by zhonzhen on 7/22/16.
 */
public class Test {
    @org.junit.Test
    public void testCase0(){
        int[][] matrix = {  {0,0,1,0},
                {0,0,1,0},
                {0,0,0,0},
                {0,0,1,0}};
        Solution s = new Solution(new Relation(matrix));
        assertEquals(2, s.checkCelebrity(3));
    }

    @org.junit.Test
    public void testCase1(){
        int[][] matrix = {  {0,1,0,0},
                {0,0,1,0},
                {0,0,0,0},
                {0,0,1,0}};
        Solution s = new Solution(new Relation(matrix));
        assertEquals(-1, s.checkCelebrity(3));
    }
}
