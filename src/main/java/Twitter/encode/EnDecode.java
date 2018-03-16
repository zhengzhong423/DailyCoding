package Twitter.encode;

public class EnDecode {
    String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    char[] chs = new char[] {'A', 'G', 'Z', 'p', 'I', 'J'};

    public static void main(String[] args) {
        EnDecode enDecode = new EnDecode();

        for (char ch: enDecode.chs) {
            int index = enDecode.base.indexOf(ch);
            if (index > -1) {
                enDecode.base = enDecode.base.substring(0, index) + enDecode.base.substring(index + 1);
            }
        }
        System.out.println(enDecode.base);
        String encoded = enDecode.encode("azyAuf*&2ui23".getBytes());
        System.out.println(encoded);
        System.out.println(new String(enDecode.decode(encoded)));
    }

    public String encode(byte[] bytes) {
        String outStr = "";
        for (int i = 0; i < bytes.length; i++) {
            outStr += base.charAt(bytes[i] >> 4);
            outStr += base.charAt((bytes[i] & 0x0f));
        }
        return outStr;
    }

    public byte[] decode(String input) {
        byte[] bytes = new byte[input.length() / 2];
        for (int i = 0; i < input.length(); i += 2) {
            int val = (base.indexOf(input.charAt(i)) << 4) + (base.indexOf(input.charAt(i + 1)));
            bytes[i / 2] = (byte) val;
        }
        return bytes;
    }
}
