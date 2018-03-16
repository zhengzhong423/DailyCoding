package Paypal.powerTwo;


import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PowerTwoTest {
    private PowerTwo pt = new PowerTwo();

    @Test
    public void testPowerTwo() {
        assertFalse(pt.isPowerTwo(87));
        assertFalse(pt.isPowerTwo(100));
        assertTrue(pt.isPowerTwo(128));
        assertTrue(pt.isPowerTwo(4));
    }
}
