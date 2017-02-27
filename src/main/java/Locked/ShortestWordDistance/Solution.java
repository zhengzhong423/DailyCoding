package Locked.ShortestWordDistance;

/**
 * Created by zhonzhen on 7/27/16.
 Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

 For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

 Given word1 = “coding”, word2 = “practice”, return 3. Given word1 = "makes", word2 = "coding", return 1.

 Note: You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.

 */
public class Solution {
    public int getMinWordDis(String[] strs, String s0, String s1){
        int index0 = -1, index1 = -1;
        int distance = Integer.MAX_VALUE;

        if(s0.equals(s1))
            throw new SameArgsException();

        for(int i = 0; i < strs.length; i++){
            if(strs[i].equals(s0)) {
                index0 = i;
                if (index1 != -1)
                    distance = Math.min(distance, index0 - index1);
            } else if(strs[i].equals(s1)){
                index1 = i;
                if(index0 != -1)
                    distance = Math.min(distance, index1 - index0);
            }
        }
        if(index0 == -1 || index1 == -1)
            throw new RuntimeException("words not found in words list");
        return distance;
    }
}
