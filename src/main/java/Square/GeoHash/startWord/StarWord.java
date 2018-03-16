package Square.GeoHash.startWord;

import java.util.ArrayList;
import java.util.List;

public class StarWord {
    TrieNode root = new TrieNode('*');

    public static void main(String[] args) {
        StarWord starWord = new StarWord();
        starWord.addWord("abc");
        starWord.addWord("uyc");

        System.out.println(starWord.getWords("*y*"));
    }

    public List<String> getWords(String str) {
        char[] chs = str.toCharArray();
        List<String> result = new ArrayList<>();
        searchWord(chs, 0, result, root);
        return result;
    }

    private void searchWord(char[] chs, int i, List<String> result, TrieNode node) {
        if (node == null) {
            return;
        }

        if (i == chs.length) {
            if (node.str != null) {
                result.add(node.str);
            }
            return;
        }

        if (chs[i] == '*') {
            for (TrieNode child: node.children) {
                searchWord(chs, i + 1, result, child);
            }
        } else {
            searchWord(chs, i + 1, result, node.children[chs[i] - 'a']);
        }
    }

    public void addWord(String str) {
        char[] chs = str.toCharArray();
        TrieNode cur = root;
        for (char ch : chs) {
            if (cur.children[ch - 'a'] == null) {
                cur.children[ch - 'a'] = new TrieNode(ch);
            }
            cur = cur.children[ch - 'a'];
        }
        cur.str = str;
    }
}

class TrieNode {
    char ch;
    TrieNode[] children = new TrieNode[26];
    String str;

    public TrieNode(char ch) {
        this.ch = ch;
    }
}