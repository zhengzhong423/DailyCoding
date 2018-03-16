package Twitter.alienLan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class AlienLan {
    public static void main(String[] args) {
        AlienLan al = new AlienLan();
        System.out.println(al.getOrder(Arrays.asList("at", "cet", "dog", "dzg")));
    }

    List<Character> getOrder(List<String> dict) {
        Map<Character, Set<Character>> seqMap = new HashMap<>();
        for (String str : dict) {
            char[] chs = str.toCharArray();
            Set<Character> tmpSet = new HashSet<>();
//            for (int i = chs.length - 1; i >= 0; i--) {
//                seqMap.putIfAbsent(chs[i], new HashSet<>());
//                Set<Character> copySet = new HashSet<>(tmpSet);
//                seqMap.get(chs[i]).addAll(copySet);
//                tmpSet.add(chs[i]);
//            }
            for (int i = 0; i < chs.length; i++) {
                seqMap.putIfAbsent(chs[i], new HashSet<>());
                Set<Character> copySet = new HashSet<>(tmpSet);
                seqMap.get(chs[i]).addAll(copySet);
                tmpSet.add(chs[i]);
            }
        }
        System.out.println(seqMap);
        LinkedList<Character> result = new LinkedList<>();
//        Set<Character> visited = new HashSet<>();
//        Stack<Character> stack = new Stack<>();
//        Iterator<Character> iter = seqMap.keySet().iterator();
//
//        while (iter.hasNext()) {
//            char ch = iter.next();
//            System.out.println(ch);
//            if (!visited.contains(ch)) {
//                dfs(ch, seqMap, visited, stack);
//            }
//            while (!stack.isEmpty()) {
//                result.add(stack.pop());
//            }
//        }

        Queue<Character> noDepChQ = new LinkedList<>();
        for (Character ch : seqMap.keySet()) {
            if (seqMap.get(ch).size() == 0) {
                noDepChQ.add(ch);
            }
        }


        while (!noDepChQ.isEmpty()) {
            Character ch = noDepChQ.poll();
            seqMap.entrySet().stream().filter(entry -> entry.getValue().contains(ch))
                    .forEach(entry -> {
                        Set<Character> set = entry.getValue();
                        set.remove(ch);
                        if (set.size() == 0) {
                            noDepChQ.add(entry.getKey());
                        }
                    });
            result.add(ch);
            System.out.println(result);
            System.out.println(seqMap);
        }
        return result;
    }

//    private void dfs(Character ch, Map<Character, Set<Character>> seqMap,
//                     Set<Character> visited, Stack<Character> stack) {
//        visited.add(ch);
//        for (char next: seqMap.get(ch)) {
//            if (!visited.contains(next)) {
//                dfs(next, seqMap, visited, stack);
//            }
//        }
//        stack.push(ch);
//    }


}
