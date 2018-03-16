//package Square.GeoHash.parseStr;
//
//public class DynamicParser {
//    public String parseDynamicStr(String str) {
//        int i = 0;
//        StringBuilder sb = new StringBuilder();
//        while (i < str.length()) {
//            char ch = str.charAt(i);
//            if (ch != '#') {
//                sb.append(ch);
//            } else {
//                if (i + 1 < str.length() && str.charAt(i + 1) == '{') {
//                    i = parseDynamicStr(str, i, sb);
//                }
//            }
//            i++;
//        }
//    }
//
//    private int parseDynamicStr(String str, int i, StringBuilder sb) {
//        i = i + 2;
//        while (i < str.length()) {
//            int index = findFirstCh(str, i);
//            while (index != -1) {
//                if (str.charAt(index) == '\'') {
//
//                }
//            }
//        }
//    }
//
//    private int findFirstCh(String str, int i) {
//        char[] chs = str.toCharArray();
//        for (; i < str.length(); i++) {
//            if (chs[i] != ' ') {
//                return i;
//            }
//        }
//        return -1;
//    }
//}
