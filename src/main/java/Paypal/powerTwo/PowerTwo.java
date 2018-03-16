package Paypal.powerTwo;

public class PowerTwo {
    public boolean isPowerTwo(int val) {
        if (val < 1) {
            return false;
        }
        while (val != 1) {
            if (val % 2 == 0) {
                val = val / 2;
            } else {
                return false;
            }
        }
        return true;
    }
}
