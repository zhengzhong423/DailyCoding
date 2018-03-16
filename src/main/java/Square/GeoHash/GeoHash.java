package Square.GeoHash;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.stream.Collectors;

public class GeoHash {
    int digit;
    String pattern = "0123456789abcdefghijklmnopqrtuv";

    public GeoHash(int digit) {
        this.digit = digit;
    }

    public static void main(String[] args) {
        GeoHash hash = new GeoHash(15);
        String str = hash.enCoding(-42.3d, 51.0d);
        System.out.println(str);
        hash.deCoding(str);
//        System.out.println(Integer.toBinaryString(Integer.parseInt("f11eh", 33)));
    }

    private void deCoding(String str) {
        BitSet bitSet = new BitSet();
        int n = 0;
        for (Character ch : str.toCharArray()) {
            int val = pattern.indexOf(ch);
            BitSet chBitSet = new BitSet();
            for (int i = 0; i < 5; i++) {
                if (val >= (int) Math.pow(2, 4 - i)) {
                    val -= (int) Math.pow(2, 4 - i);
                    chBitSet.set(i);
                }
            }
            for (int i = 0; i < 5; i++) {
                bitSet.set(n * 5 + i, chBitSet.get(i));
            }
            n++;
        }

        for (int i = 0; i < str.length() * 5; i++) {
            System.out.print(bitSet.get(i) ? 1 : 0);
        }

        double longS = -180.0d, longE = 180.0d, latS = -90.0d, latE = 90.0d;
        for (int i = 0; i < digit; i++) {
            if (bitSet.get(i * 2)) {
                longS = (longE + longS) / 2;
            } else {
                longE = (longE + longS) / 2;
            }

            if (bitSet.get(i * 2 + 1)) {
                latS = (latE + latS) / 2;
            } else {
                latE = (latE + latS) / 2;
            }
        }

        System.out.println(longS + "," + longE);
        System.out.println(latS + "," + latE);
    }



    public String enCoding(double longitude, double latitude) {
        BitSet bitSet = new BitSet(digit * 2);
        double longS = -180.0d, longE = 180.0d, latS = -90.0d, latE = 90.0d;
        for (int i = 0; i < digit; i++) {
            boolean bitValLong = getBitVal(longS, longE, longitude);
            bitSet.set(2 * i, bitValLong);
            if (!bitValLong) {
                longE = (longS + longE) / 2;
            } else {
                longS = (longS + longE) / 2;
            }
//            System.out.println(longS + "," + longE);
            boolean bitValLat = getBitVal(latS, latE, latitude);
            bitSet.set(2 * i + 1, bitValLat);
            if (!bitValLat) {
                latE = (latS + latE) / 2;
            } else {
                latS = (latS + latE) / 2;
            }
        }

//        for (int i = 0; i < digit * 2; i++) {
//            System.out.print(bitSet.get(i) ? 1 : 0);
//        }

        String str = "";
        for (int i = 0; i < digit * 2; i = i + 5) {
            str += getChar(bitSet.get(i, i + 5));
        }
        return str;
    }

    private Character getChar(BitSet bitSet) {
        int num = 0;
        for (int i = 0; i < 5; i++) {
            num += ((int)Math.pow(2, 4 - i)) * (bitSet.get(i) ? 1 : 0);
        }

        return pattern.charAt(num);
    }

    private boolean getBitVal(double s, double e, double val) {
        double mid = (s + e) / 2.0d;
        return val >= mid;
    }
}
