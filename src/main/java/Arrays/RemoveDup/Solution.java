package Arrays.RemoveDup;


/**
 * Created by zhonzhen on 7/6/16.
 */

public class Solution {
    int removeDuplicates(int[] a){
        int i = 1, j = 0;

        if(a.length == 0)
            return 0;

        for(; i < a.length; i++){
            if(a[i] == a[j])
                continue;
            j++;
            a[j] = a[i];
        }
        return j + 1;
    }

}
