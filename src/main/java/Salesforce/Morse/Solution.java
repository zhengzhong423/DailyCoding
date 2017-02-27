package Salesforce.Morse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by zhonzhen on 11/4/16.
 */
public class Solution {
    int MAX_TEXT_COUNT = 100;
    int MAX_WORD_LEN = 10;
    MorseCode morseCode = new MorseCode();
    StringBuilder sb = new StringBuilder();

    private void commandReader() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command = null;

        // read morse map
        while (true) {
            try {
                command = br.readLine();
                if (command.trim().equals("*")) {
                    break;
                }
                this.readMap(command);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // read text
        int counter = 0;
        while (counter < MAX_TEXT_COUNT) {
            try {
                command = br.readLine();
                if (command.trim().equals("*")) {
                    break;
                }
                if (command.trim().length() > MAX_WORD_LEN) {
                    System.out.format("[Invalid] word length exceed max length %d\n", MAX_WORD_LEN);
                    continue;
                }

                this.readText(command);
                counter++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // read morse map
        while (true) {
            try {
                command = br.readLine();
                if (command.trim().equals("*")) {
                    break;
                }
                this.readCode(command);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        output();
    }

    private void output() {
        List<String> ret = morseCode.parseCode(sb.toString());
        for(String str: ret)
            System.out.println(str);
    }

    private void readCode(String command) {
        sb.append(command);
        sb.append(" ");
    }

    private void readText(String command) {
        morseCode.addText(command);
    }

    private void readMap(String command) {
        // handle multiple spaces
        if (command == null){
            System.out.println("[Error] Read null command");
            return;
        }
        String[] strs = command.trim().split("\\s+");
        if (strs.length != 2) {
            System.out.println("[Invalid] Invalid morse map input format");
            return;
        }

        if (strs[0] == null || strs[1] == null || strs[0].length() != 1) {
            System.out.println("[Invalid] Invalid morse map character format");
            return;
        }

        morseCode.addMorse(strs[0].charAt(0), strs[1]);

    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.commandReader();
    }

}
