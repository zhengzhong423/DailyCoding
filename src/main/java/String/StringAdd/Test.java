package String.StringAdd;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by zhonzhen on 10/19/16.
 */
public class Test {
    Solution sol = new Solution();

    @org.junit.Test
    public void strAddTest() {
        assertEquals("345", sol.strAdd("123", "222"));

        assertEquals("1234", sol.strAdd("9", "1225"));

        assertEquals("12345", sol.strAdd("5555", "6790"));

        assertEquals("1000000", sol.strAdd("1", "999999"));
    }
}
