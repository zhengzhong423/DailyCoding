package Amazon.Celebrity;

import java.util.LinkedList;

/**
 * Created by zhonzhen on 7/22/16.
 */
public class Solution extends Relation {

    int[][] matrix;

    public Solution(Relation r){
        this.matrix = r.matrix;
        super.matrix = r.matrix;
    }

    int checkCelebrity(int n){
        LinkedList<Integer> l = new LinkedList<Integer>();
        for(int i = 0; i < matrix.length; i++)
            l.add(i);
        LinkedList<Integer> l_copy = new LinkedList<Integer>(l);
        while(l.size() > 1){
            int a = l.removeFirst();
            int b = l.removeFirst();
            if(knows(a, b)) {
                l.remove(new Integer(a));
                l.add(b);
            }
            else {
                l.remove(new Integer(b));
                l.add(a);
            }
        }

        //Need to check if the candidate is a real celebrity
        int candidate = l.poll();
        for(int i = 0; i < l_copy.size(); i++){
            if((i != candidate) &&
                    (!knows(i, candidate) || knows(candidate, i)))
                return -1;
        }


        return candidate;
    }
}
