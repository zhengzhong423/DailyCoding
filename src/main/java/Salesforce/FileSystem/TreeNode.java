package Salesforce.FileSystem;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhonzhen on 11/3/16.
 */
public class TreeNode {
    TreeNode parent;
    List<TreeNode> children = new LinkedList<TreeNode>();
    Map<String, TreeNode> children_map = new HashMap<String, TreeNode>();
    boolean isFile;
    String name;

    public TreeNode(String name, TreeNode parent, boolean isFile) {
        this.name = name;
        this.parent = parent;
        this.isFile = isFile;
    }

    public void addChild(TreeNode child) {
        children.add(child);
        children_map.put(child.name, child);
    }

    public boolean hasChild(String name) {
        return children_map.containsKey(name);
    }

    public TreeNode getChild(String name) {
        return children_map.containsKey(name) ? children_map.get(name) : null;
    }
}
