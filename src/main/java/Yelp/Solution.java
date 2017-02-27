package Yelp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhonzhen on 2/2/17.
 */
public class Solution {
    public static void main(String[] args) {

    }

    public String findRestaurant(List<String> l1, List<String> l2) {
        String ret = "Yelpwich";
        int score = Integer.MAX_VALUE;
        Map<String, Integer> map = new HashMap<String, Integer>();

        if(l1 == null || l2 == null)
            return null;

        for(int i = 0; i < l1.size(); i++) {
            map.put(l1.get(i), i);
        }

        for(int i = 0; i < l2.size(); i++) {
            String name = l2.get(i);
            if(map.containsKey(name) && score < map.get(name) + i) {
                ret = name;
                score = map.get(name) + i;
            }
        }

        return ret;
    }

}
