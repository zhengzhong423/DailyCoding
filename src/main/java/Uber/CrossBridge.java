package Uber;

import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class CrossBridge {

    private boolean findSolution;
    private Integer prev;

    public static void main(String[] args) {
        new CrossBridge().travel();
    }

    public void travel() {
        Side sideA = new Side("A");
        sideA.add(0);
        sideA.add(1);
        sideA.add(2);
        sideA.add(3);
        Side sideB = new Side("B");

        backTrack(sideA, sideB, 0);
    }

    private void backTrack(Side sideA, Side sideB, int i) {
        Side activeSide = i % 2 == 0 ? sideA : sideB;
        if (sideB.isFinished() || findSolution) {
            System.out.println(i);
            findSolution = true;
            return;
        }

        Set<Integer> set = new HashSet<>(activeSide.roles);
        Integer curPrev = prev;
        for (Integer role: set) {
            if (role == 0) {
                sideA.leave();
                sideB.add(0);
                if (sideA.isSafe()) {
                    if (curPrev == null || curPrev != 0) {
                        prev = 0;
                        backTrack(sideA, sideB, i + 1);
                        prev = curPrev;
                    }
                }
                sideA.add(0);
                sideB.leave();
            } else {
                sideA.leave(role);
                sideB.add(0);
                sideB.add(role);
                if (sideA.isSafe()) {
                    if (curPrev == null || curPrev != 0) {
                        prev = role;
                        backTrack(sideA, sideB, i + 1);
                        prev = curPrev;
                    }
                }
                sideB.leave(role);
                sideA.add(role);
                sideA.add(0);
            }
        }
    }
}

class Side {
    String name;
    Set<Integer> roles = new HashSet<>();

    public Side(String name) {
        this.name = name;
    }

    public void add(Integer role) {
        roles.add(role);
    }

    public boolean leave(Integer addOn) {
        if (!leave()) {
            return false;
        }
        roles.remove(addOn);
        return true;
    }

    public boolean leave() {
        if (roles.contains(0)) {
            roles.remove(0);
        } else {
            return false;
        }
        return true;
    }

    public boolean isSafe() {
        if (roles.contains(0)) {
            return true;
        }

        if (roles.size() == 2 &&
                ((roles.contains(1) && roles.contains(2)) || (roles.contains(1) && roles.contains(2)))) {
            return false;
        }

        return true;
    }

    public boolean isFinished() {
        return roles.size() == 4;
    }
}
