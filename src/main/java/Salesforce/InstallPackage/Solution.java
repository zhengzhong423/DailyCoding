package Salesforce.InstallPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhonzhen on 11/2/16.
 */
public class Solution {
    HashMap<String, Set<String>> hash = new HashMap<String, Set<String>>();
    HashMap<String, Set<String>> inverted_hash = new HashMap<String, Set<String>>();
    Set<String> installed = new HashSet<String>();

    private void commandReader() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command = null;

        while(command == null || !command.trim().equals("END")) {
            try {
                command = br.readLine().toUpperCase();
                this.read(command);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void read(String command) {
        if(command == null || command.trim().length() == 0)
            return;

        String[] strs = command.split("\\s+");

        if (strs[0].equals("DEPEND")) {
            addDependencies(strs);
        } else if(strs[0].equals("INSTALL")) {
            installComp(strs[1], new HashSet<String>());
        } else if(strs[0].equals("REMOVE")) {
            removeComp(strs[1]);
        } else if(strs[0].equals("LIST")) {
            System.out.println(installed);
        } else if(strs[0].equals("DEBUG")) {
            System.out.println(hash);
            System.out.println(inverted_hash);
        }
    }

    private void removeComp(String str) {
        if(!installed.contains(str)) {
            System.out.println(str + " not installed");
            return;
        }

        if(inverted_hash.containsKey(str)) {
            for (String comp : inverted_hash.get(str)) {
                if (installed.contains(comp)) {
                    System.out.println(str + " still needed");
                    return;
                }
            }
        }


        if(hash.containsKey(str)) {
            for (String requirement : hash.get(str)) {
                boolean uninstall = true;
                if (installed.contains(requirement)) {
                    for (String comp : inverted_hash.get(requirement)) {
                        if (installed.contains(comp) && !comp.equals(str)) {
                            uninstall = false;
                        }
                    }
                    if (uninstall) {
                        System.out.println("Removing " + requirement);
                        if (installed.contains(requirement))
                            installed.remove(requirement);
                    }
                }
            }
        }

        System.out.println("Removing " + str);
        if(installed.contains(str))
            installed.remove(str);
    }

    private void installComp(String str, HashSet<String> visited) {

        if(installed.contains(str)) {
            if(visited.isEmpty()) {
                System.out.println(str + " already installed");
            }
            return;
        }

        if(visited.contains(str))
            return;
        visited.add(str);

        if(hash.containsKey(str)) {
            for(String pre: hash.get(str)) {
                installComp(pre, visited);
            }
        }

        installed.add(str);
        System.out.println("Installing " + str);
    }

    private void addDependencies(String[] strs) {
        hash.put(strs[1], new HashSet<String>());
        for(int i = 2; i < strs.length; i++) {
            hash.get(strs[1]).add(strs[i]);
            if (!inverted_hash.containsKey(strs[i])) {
                inverted_hash.put(strs[i], new HashSet<String>());
            }
            inverted_hash.get(strs[i]).add(strs[1]);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.commandReader();
    }
}















