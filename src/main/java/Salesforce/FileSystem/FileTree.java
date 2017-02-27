package Salesforce.FileSystem;

import com.sun.deploy.util.StringUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by zhonzhen on 11/3/16.
 */
public class FileTree {
    TreeNode root = new TreeNode(null, null, false);

    public FileTree() {

    }

    public TreeNode getRoot() {
        return root;
    }

    public boolean mkdir(String name, TreeNode curDir) {
        if(curDir.children_map.containsKey(name)) {
            System.out.println("Same name");
            return false;
        }
        curDir.addChild(new TreeNode(name, curDir, false));
        return true;
    }

    public boolean touch(String name, TreeNode curDir) {
        if(curDir.children_map.containsKey(name)) {
            System.out.println("Same name");
            return false;
        }
        curDir.addChild(new TreeNode(name, curDir, true));
        return true;
    }

    public TreeNode getDir(String path, TreeNode cur) {
        if(path == null)
            return null;
        String[] strs = path.split("/");
        TreeNode curDir = cur;
        if(strs.length == 0)
            curDir = root;

        for(int i = 0; i < strs.length; i++) {
            if(strs[i].length() == 0 && i > 0 && i < strs.length - 1)
                return null;
            if(strs[i].length() == 0 && i < strs.length - 1) {
                if(curDir.isFile)
                    return null;
                else
                    break;
            }

            if(strs[i].equals("..")) {
                curDir = curDir.parent == null ? root : curDir.parent;
            } else if(strs[i].equals(".")) {
            } else {
                if(curDir.hasChild(strs[i]))
                    curDir = curDir.getChild(strs[i]);
                else
                    return null;
            }
        }
        return curDir.isFile ? null : curDir;
    }

    public String getPath(TreeNode curDir) {
        Stack<String> stack = new Stack<String>();
        while(curDir.parent != null) {
            stack.push(curDir.name);
            curDir = curDir.parent;
        }

        List<String> dir_list = new LinkedList<String>();
        while(!stack.isEmpty()) {
            dir_list.add(stack.pop());
        }
        return "/" + StringUtils.join(dir_list, "/");
    }

    public void list(TreeNode cur) {
        if(cur.isFile) {
            System.out.println("This is a File");
            return;
        }
        System.out.println(".");
        System.out.println("..");
        for(String str : cur.children_map.keySet()) {
            if(cur.children_map.get(str).isFile)
                System.out.println(str);
            else
                System.out.println(str + "/");
        }
    }

    public List<String> find(String pattern, TreeNode node) {

        List<String> files_list = new LinkedList<String>();
        if(node.isFile)
            return files_list;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(node);
        while(!stack.isEmpty()) {
            TreeNode tmp = stack.pop();
            for(TreeNode child: tmp.children) {
                if(child != null) {
                    if(matchPattern(pattern, child.name)) {
                        files_list.add(getPath(child));
                    }
                    if (!child.isFile) {
                        stack.push(child);
                    }
                }
            }
        }
        return files_list;
    }

    private boolean matchPattern(String pattern, String name) {
        return name.matches(pattern.replaceAll("\\*", "\\.\\*"));
    }
}
