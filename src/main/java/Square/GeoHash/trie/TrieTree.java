package Square.GeoHash.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class TreeNode {
    char ch;
    TreeNode[] children = new TreeNode[26];
    boolean isLeaf;

    public TreeNode(char ch) {
        this.ch = ch;
    }
}

public class TrieTree {


    TreeNode root;

    public static void main(String[] args) {
        TrieTree tree = new TrieTree();
        tree.add("accb");
        System.out.println(tree.findList(tree.root, "a**b"));
    }

    public TrieTree() {
        root = new TreeNode('*');
    }

    public void add(String str) {
        TreeNode cur = root;
        for (char ch: str.toCharArray()) {
            if (cur.children[ch - 'a'] == null) {
                cur.children[ch - 'a'] = new TreeNode(ch);
            }
            cur = cur.children[ch - 'a'];
        }
        cur.isLeaf = true;
    }

    public List<String> findList(TreeNode node, String word) {
        List<String> wordList = new ArrayList<>();
        wordList.add("");
        for (char ch: word.toCharArray()) {
            List<String> tmp = new ArrayList<>();
            for (String w : wordList) {
                if (ch == '*') {
                    for (int j = 0; j < 26; j++) {
                        tmp.add(w + (char)('a' + j));
                    }
                } else {
                    tmp.add(w + ch);
                }
            }
            wordList = tmp;
        }
        return wordList.stream().filter(e -> find(node, e)).collect(Collectors.toList());
    }

    public boolean find(TreeNode node, String word) {
        for (char ch: word.toCharArray()) {
            if (node.children[ch - 'a'] != null) {
                node = node.children[ch - 'a'];
            } else {
                return false;
            }
        }
        return node.isLeaf;
    }
}
