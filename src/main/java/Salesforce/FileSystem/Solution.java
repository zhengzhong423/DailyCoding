package Salesforce.FileSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by zhonzhen on 11/3/16.
 */
public class Solution {

    TreeNode cur;
    FileTree ft;

    private void commandReader() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command = null;

        while(true) {
            try {
                command = br.readLine();
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

        if(strs[0].equals("pwd")) {
            System.out.println(this.ft.getPath(cur));
        } else if(strs[0].equals("cd")) {
            if(strs.length == 1) {
                cur = ft.getRoot();
                return;
            }
            TreeNode tmp = ft.getDir(strs[1], cur);
            if(tmp == null)
                System.out.println("Invalid path");
            else
                cur = tmp;
        } else if(strs[0].equals("mkdir")) {
            ft.mkdir(strs[1], cur);
        } else if(strs[0].equals("touch")) {
            ft.touch(strs[1], cur);
        } else if(strs[0].equals("ls")) {
            ft.list(cur);
        } else if(strs[0].equals("find")) {
            TreeNode tmp = ft.getDir(strs[2], cur);
            if(tmp == null) {
                System.out.println("Invalid Path");
                return;
            }
            System.out.println(ft.find(strs[1], tmp));
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.ft = new FileTree();
        sol.cur = sol.ft.getRoot();
        sol.commandReader();
    }
}
