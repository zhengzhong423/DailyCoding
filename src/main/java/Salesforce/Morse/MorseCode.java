package Salesforce.Morse;

import java.util.*;

/**
 * Created by zhonzhen on 11/4/16.
 */
public class MorseCode {
    int MAX_MORSE_LEN = 6;

    Map<Character, String> morseMap = null;
    Set<String> codeSet = null;

    Map<String, String> morseToText = null;
    Map<String, String> textToMorse = null;

    public MorseCode() {
        morseMap = new HashMap<Character, String>();
        codeSet = new HashSet<String>();

        morseToText = new HashMap<String, String>();
        textToMorse = new HashMap<String, String>();
    }

    public boolean addMorse(Character ch, String str) {
        // check if character already exists
        if (morseMap.containsKey(ch)) {
            System.out.println("[Invalid] Key already exists");
            return false;
        }

        // check if character 0-9 or A-Z
        if (!((ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'Z'))) {
            System.out.println("[Invalid] Character not acceptable");
            return false;
        }

        // check if morse code already exists
        if (codeSet.contains(str)) {
            System.out.println("[Invalid] Morse code already exists");
            return false;
        }

        // check if morse code too long
        if (str.length() > MAX_MORSE_LEN) {
            System.out.format("[Invalid] Morse code longer then %d\n", MAX_MORSE_LEN);
            return false;
        }

        // check if morse code valid
        if (!validMorse(str)) {
            System.out.println("[Invalid] Invalid morse map code format " + str);
            return false;
        }

        codeSet.add(str);
        morseMap.put(ch, str);
        return true;
    }

    public boolean addText(String str) {
        if (str == null || str.trim().length() == 0)
            return false;
        char[] chs = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char ch: chs) {
            // check if text has invalid character
            if (!morseMap.containsKey(ch)) {
                System.out.format("[Invalid] Text has invalid character : %s\n", ch);
                return false;
            }
            sb.append(morseMap.get(ch));
        }
        morseToText.put(sb.toString(), str);
        textToMorse.put(str, sb.toString());
        return true;
    }

    public List<String> parseCode(String str) {
        List<String> retList_tmp = new LinkedList<String>();
        List<String> retList = new LinkedList<String>();
        Set<String> dup = new HashSet<String>();
        String[] strs = str.split("\\s+");

        for(String code: strs) {
            if (!validMorse(code)) {
                System.out.println(code + " not a valid morse");
                return null;
            }
            if (morseToText.containsKey(code)) {
                // Perfect match, check dup
                String text = morseToText.get(code);
                if(retList_tmp.contains(text))
                    dup.add(text);
                retList_tmp.add(text);
            } else {
                // Not perfect match, add '?'
                String text = findNearestText(code);
                if(text != null && text.length() != 0)
                    retList_tmp.add(text + "?");
            }
        }



        // add '!' to dup element
        for (int i = 0; i < retList_tmp.size(); i++) {
            String tmpStr = retList_tmp.get(i);
            if (dup.contains(tmpStr))
                retList.add(tmpStr + "!");
            else
                retList.add(tmpStr);
        }

        return retList;
    }

    private String findNearestText(String code) {
        int minLen = Integer.MAX_VALUE;
        String str = "";
        for(String key: morseToText.keySet()) {
            if(key.startsWith(code) && minLen > morseToText.get(key).length()) {
                minLen = morseToText.get(key).length();
                str = morseToText.get(key);
            }
        }
        return str;
    }

    private boolean validMorse(String str) {
        return  str.matches("[\\.\\-]+");
    }

}
