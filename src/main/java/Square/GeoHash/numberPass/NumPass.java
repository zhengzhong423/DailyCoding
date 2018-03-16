package Square.GeoHash.numberPass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Node {
    int val;
    Set<Integer> dependencySet = new HashSet<>();

    public Node(int val) {
        this.val = val;
    }
}
public class NumPass {

    public static void main(String[] args) {
        NumPass numPass = new NumPass();
        System.out.println(numPass.passNumber(Arrays.asList(new String[] {"10", "A0+5", "A0+A1+8"})));
    }
    public List<Integer> passNumber(List<String> list) {
        int i = 0;
        Map<Integer, Node> map = new HashMap<>();
        Queue<Integer> numQueue = new LinkedList<>();
//        Map<Integer, Set<Integer>> invertedMap = new HashMap<>();

        for (String str: list) {
            Node node = new Node(0);
            String[] strs = str.split("\\+");
            for (String s: strs) {
                if (s.startsWith("A")) {
                    int dependency = Integer.parseInt(s.substring(1));
                    node.dependencySet.add(dependency);
//                    Set<Integer> set = invertedMap.getOrDefault(dependency, new HashSet<>());
//                    set.add(i);
//                    invertedMap.put(dependency, set);
                } else {
                    node.val += Integer.parseInt(s);
                }
            }
            if (node.dependencySet.size() == 0) {
                numQueue.add(i);
            }
            map.put(i++, node);
        }
        while (!numQueue.isEmpty()) {
            Integer num = numQueue.poll();
            for (Integer index: map.keySet()) {
                Node node = map.get(index);
                if (node.dependencySet.contains(num)) {
                    node.dependencySet.remove(num);
                    node.val += map.get(num).val;
                    if (node.dependencySet.isEmpty()) {
                        numQueue.add(index);
                    }
                }
            }
        }

        List<Integer> rs = new ArrayList<>();
        for (Integer index: map.keySet()) {
            rs.add(map.get(index).dependencySet.size() > 0 ? null: map.get(index).val);
        }

        return rs;
    }
}
