package Square.GeoHash.riverCross;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class RiverCross {
//     = new ArrayList<>();
    List<List<Integer>> steps = new ArrayList<>();

    public static void main(String[] args) {
        RiverCross rc = new RiverCross();
        List<Integer> status = new ArrayList<>();
        IntStream.range(0, 4).forEach(i -> status.add(0));
        rc.dfs(status, new ArrayList<>(), new ArrayList<>());

        System.out.println(rc.steps);
    }


    private boolean isSafe(List<Integer> status) {
        if (status.get(1) != status.get(0) && status.get(1) == status.get(2)) {
            return false;
        }

        if (status.get(2) != status.get(0) && status.get(2) == status.get(3)) {
            return false;
        }

        return true;
    }


    public void dfs(List<Integer> status, List<List<Integer>> visited, List<List<Integer>> cur) {
        if (!isSafe(status)) {
            return;
        }
        cur.add(new ArrayList<>(status));
        if (checkFinished(status)) {
            steps.addAll(new ArrayList<>(cur));
            return;
        }

        List<Integer> tmpStatus = new ArrayList<>(status);
        // farmer goes alone
        int prev0 = status.get(0);
        tmpStatus.set(0, 1 - status.get(0));
        if (!cur.contains(tmpStatus)) {
            visited.add(new ArrayList<>(tmpStatus));
            dfs(tmpStatus, visited, cur);
        }
        tmpStatus.set(0, prev0);

        // with some other animal
        for (int i = 1; i <= 3; i++) {
            if (tmpStatus.get(i) == prev0) {
                int prevVal = tmpStatus.get(i);
                tmpStatus.set(0, 1 - tmpStatus.get(0));
                tmpStatus.set(i, 1 - tmpStatus.get(i));
                if (!cur.contains(tmpStatus)) {
                    visited.add(new ArrayList<>(tmpStatus));
                    dfs(tmpStatus, visited, cur);
                }
                tmpStatus.set(i, prevVal);
                tmpStatus.set(0, prev0);
            }
        }

    }

    private boolean checkFinished(List<Integer> status) {
        for (Integer val : status) {
            if (val == 0) {
                return false;
            }
        }
        return true;
    }
}
