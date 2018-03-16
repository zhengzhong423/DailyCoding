package Twitter.Permucation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PermutationUtils {
    public static void main(String[] args) {
        PermutationUtils utils = new PermutationUtils();
        System.out.println(utils.getPermutation(Arrays.asList(new Integer[]{8,8,4,5}).stream().sorted().collect(Collectors.toList()))
                .stream().distinct().collect(Collectors.toList()));
    }

    public List<List<Integer>> getPermutation(List<Integer> list) {
        List<List<Integer>> rs = new ArrayList<>();
        if (list.size() == 1) {
            rs.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{list.get(0)})));
            return rs;
        }
        for (int i = 0; i < list.size(); i++) {
            List<Integer> tmpInput = new ArrayList<>(list);
            tmpInput.remove(i);
            List<List<Integer>> tmpRs = getPermutation(tmpInput);
            Integer val = list.get(i);
            tmpRs.forEach(e ->
                e.add(0, val));
            rs.addAll(tmpRs);
        }
        return rs;
    }

}
