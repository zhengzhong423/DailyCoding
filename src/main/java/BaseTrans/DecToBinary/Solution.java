package BaseTrans.DecToBinary;

/**
 * Created by zhonzhen on 7/6/16.
 */
public class Solution {
    String decToBianry(int num){
        StringBuilder sb = new StringBuilder();
        while(num > 0){
            if(num % 2 == 0)
                sb.insert(0, "0");
            else
                sb.insert(0, "1");
            num = num / 2;
        }
        return sb.toString();
    }
}
