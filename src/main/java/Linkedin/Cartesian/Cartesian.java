package Linkedin.Cartesian;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cartesian {

    public static void main(String[] args) {
        Cartesian c = new Cartesian();
        List<Set<Integer>> input = new ArrayList<>();
        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        Set<Integer> set2 = new HashSet<>();
        set2.add(4);
        set2.add(5);

        Set<Integer> set3 = new HashSet<>();
        set3.add(6);

        input.add(set1);
        input.add(set2);
        input.add(set3);

        System.out.println(c.getProduct(input));
    }
    private List<List<Integer>> getProduct(List<Set<Integer>> input) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());

        for (Set<Integer> set: input) {
            List<List<Integer>> tmpRs = new ArrayList<>();
            for (List<Integer> tmp: result) {
                for (Integer num : set) {
                    List<Integer> newTmp = new ArrayList<>(tmp);
                    newTmp.add(num);
                    tmpRs.add(newTmp);
                }
            }
            result = tmpRs;
        }
        return result;
    }
}
