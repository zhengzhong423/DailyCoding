package Thumback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhonzhen on 10/23/16.
 */
public class SimpleDB {

    HashMap<String, String> map = new HashMap<String, String>();
    HashMap<String, Set<String>> invert_map = new HashMap<String, Set<String>>();
    StringBuilder reverse_command = new StringBuilder();

    String[] ACTIONS = {"SET", "UNSET", "NUMEQUALTO","GET", "BEGIN", "COMMIT", "ROLLBACK"};
    String END_ACTION = "END";

    private void commandReader() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command = null;

        while(command == null || !command.trim().equals("END")) {
            try {
                command = br.readLine().toUpperCase();
                this.parser(command, false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void parser(String command, boolean rollback_mode) {
        if(command == null || command.trim().length() == 0)
            return;
        // split by any number of spaces
        String[] strs = command.trim().split("\\s+");
        strs[0].toUpperCase();
        // set action
        if (strs[0].equals(ACTIONS[0])) {

            if(reverse_command.length() > 0 && !rollback_mode && strs.length == 3)
                prep_rollback_call(strs[1]);

            if(strs.length == 3 && map.containsKey(strs[1]))
                unset_invert_map(map.get(strs[1]), strs[1]);

            if (!ACT_SET(strs))
                System.out.println("%% INVALID INPUT");

        } else if (strs[0].equals(ACTIONS[1])) {
            //unset action

            if(reverse_command.length() > 0 && !rollback_mode && strs.length == 2)
                prep_rollback_call(strs[1]);

            if(strs.length == 2 && map.containsKey(strs[1]))
                unset_invert_map(map.get(strs[1]), strs[1]);

            if(!ACT_UNSET(strs))
                System.out.println("%% INVALID INPUT");

        } else if(strs[0].equals(ACTIONS[2])) {
            //numequalto action

            if (strs.length != 2) {
                System.out.println("%% INVALID INPUT");
                return;
            }
            System.out.println(ACT_NUMEQUALTO(strs));

        } else if(strs[0].equals(ACTIONS[3])) {
            //get action

            if (strs.length != 2) {
                System.out.println("%% INVALID INPUT");
                return;
            }
            System.out.println(ACT_GET(strs));
        } else if(strs[0].equals(ACTIONS[4])) {
            //begin action
            ACT_BEGIN(strs);

        } else if(strs[0].equals(ACTIONS[5])) {
            //commit action
            ACT_COMMIT(strs);
        } else if(strs[0].equals(ACTIONS[6])) {
            //rollback action
            ACT_ROLLBACK(strs);
        } else {
            System.out.println("%% INVALID INPUT");
        }
        System.out.println(reverse_command);
    }

    private void unset_invert_map(String val, String key) {
        if(invert_map.containsKey(val))
            if (invert_map.get(val).contains(key))
                invert_map.get(val).remove(key);
    }

    private void ACT_ROLLBACK(String[] strs) {
        if (strs.length != 1) {
            System.out.println("%% INVALID INPUT");
            return;
        }
        if(reverse_command.length() == 0) {
            System.out.println("NO TRANSACTION");
            return;
        }
        if(reverse_command.indexOf("#") == 0) {
            reverse_command = new StringBuilder(reverse_command.substring(1));
            return;
        }

        int index;
        if((index = reverse_command.indexOf("#")) > 0) {
            String commands = reverse_command.substring(0, index);
            String[] commands_arr = commands.split(",");
            for(int i = 0; i < commands_arr.length; i++) {
                parser(commands_arr[i], true);
            }
            reverse_command = new StringBuilder(reverse_command.substring(index + 1));
        }

    }

    private void ACT_COMMIT(String[] strs) {
        if (strs.length != 1) {
            System.out.println("%% INVALID INPUT");
            return;
        }
        reverse_command = new StringBuilder();
    }

    private void ACT_BEGIN (String[] strs) {
        if (strs.length != 1) {
            System.out.println("%% INVALID INPUT");
            return;
        }
        reverse_command.insert(0, "#");
    }

    private String ACT_GET(String[] strs) {
        if (!map.containsKey(strs[1]))
            return null;
        return map.get(strs[1]);
    }

    private int ACT_NUMEQUALTO(String[] strs) {
        if(invert_map.containsKey(strs[1]))
            return invert_map.get(strs[1]).size();
        return 0;
    }

    private boolean ACT_SET(String[] strs) {
        if (strs.length != 3 ) {
            return false;
        }
        if(strs[1].indexOf('#') >= 0) {
            System.out.println("%% UNSUPPORTED VARIABLE NAME");
            return false;
        }
        map.put(strs[1], strs[2]);
        invert_add(invert_map, strs[2], strs[1]);
        return true;
    }

    private boolean ACT_UNSET(String[] strs) {
        if (strs.length != 2 ) {
            return false;
        }

        if(!map.containsKey(strs[1]))
            return true;
        String val = map.get(strs[1]);
        map.remove(strs[1]);
        invert_remove(invert_map, val, strs[1]);
        return true;
    }

    private void invert_add(HashMap<String, Set<String>> invert_map, String value, String key) {
        if (!invert_map.containsKey(value)) {
            invert_map.put(value, new HashSet<String>());
        }
        invert_map.get(value).add(key);
    }

    private void invert_remove(HashMap<String, Set<String>> invert_map, String value, String key) {
        if (invert_map.containsKey(value)) {
            invert_map.get(value).remove(key);
        }
    }

    private void prep_rollback_call(String key) {

        if(map.containsKey(key)) {
            reverse_command.insert(0, "SET " + key + " " + map.get(key) + ",");
        } else {
            reverse_command.insert(0, "UNSET " + key + ",");
        }
    }


    public static void main(String[] args) {
        SimpleDB sdb = new SimpleDB();
        sdb.commandReader();
    }
}
