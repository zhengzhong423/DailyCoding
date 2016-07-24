package Arrays.RemoveDupII;

/**
 * Created by zhonzhen on 7/6/16.
 */
public class Solution {
    int removeDuplicates(int[] a){
        int i = 1, j = 0, num = 1;
        if(a.length == 0)
            return 0;
        for(; i < a.length; i++){
            if(a[i] == a[j]){
                num ++;
                if(num > 2){
                    continue;
                }
            }
            else
                num = 1;
            j++;
            a[j] = a[i];
        }
        return j + 1;
    }

}
