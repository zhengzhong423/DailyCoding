package Square.GeoHash.DropBall;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DropBall {
    final int M = 10;
    final int N = 15;
    int[][] grid = new int[M][N];

    public static void main(String[] args) {
        DropBall db = new DropBall();
//        db.drop(0, 1);
//        db.drop(2, 1);
//        db.drop(1,1);

        db.drop(5, 7);
        db.drop(5, 3);
        db.drop(3, 1);
        db.drop(4, 2);
        db.drop(4, 1);
        db.drop(4, 3);
        for (int i = 0; i < db.grid.length; i++) {
            System.out.println(Arrays.toString(db.grid[i]));
        }
        System.out.println();
        db.drop(3, 1);

        for (int i = 0; i < db.grid.length; i++) {
            System.out.println(Arrays.toString(db.grid[i]));
        }
    }

    public void drop(int y, int color) {
        int initialX = findAvailableX(y);
        List<Point> affectedNeighbors = checkNeighbor(initialX, y, color);
        if (initialX < grid.length - 1 && grid[initialX + 1][y] == color) {
            grid[initialX + 1][y] = 0;
        } else {
            grid[initialX][y] = color;
        }

        while (affectedNeighbors.size() > 0) {
            Set<Integer> affectedYs = removePoints(affectedNeighbors);
            affectedNeighbors.clear();
            for (Integer yAxiel: affectedYs) {
                for (int m = grid.length - 1; m >= 0 && grid[m][yAxiel] != 0; m--) {
                    List<Point> tmp = checkNeighbor(m, yAxiel, grid[m][yAxiel]);
                    if (tmp.size() != 0) {
                        affectedNeighbors.addAll(tmp);
                    }
                }
            }
        }
    }

    private Set<Integer> removePoints(List<Point> affectedNeighbors) {
        for (Point p : affectedNeighbors) {
            grid[p.x][p.y] = -1;
        }

        Set<Integer> affectedYs =
                affectedNeighbors.stream().map(e -> e.y).collect(Collectors.toSet());

        for (Integer y : affectedYs) {
            int fastPtr = grid.length - 1;
            int slowPtr = grid.length - 1;
            while (grid[slowPtr][y] != 0) {
                while (fastPtr >= 0 && grid[fastPtr][y] == -1) {
                    fastPtr--;
                }

                if (fastPtr >= 0) {
                    grid[slowPtr][y] = grid[fastPtr][y];
                } else {
                    grid[slowPtr][y] = 0;
                }
                slowPtr--;
                fastPtr--;
            }
            for (int i = slowPtr - 1; i >= 0 && grid[i][y] != 0; i--) {
                grid[i][y] = 0;
            }
        }

        return affectedYs;
    }

    private List<Point> checkNeighbor(int x, int y, int color) {
        List<Point> neighbors = new ArrayList<>();
        boolean added = false;
        if (y - 1 >= 0 && grid[x][y - 1] == color) {
            neighbors.add(new Point(x, y - 1));
            neighbors.add(new Point(x, y));
            added = true;
        }

        if (y + 1 < grid[0].length && grid[x][y + 1] == color) {
            neighbors.add(new Point(x, y + 1));
            if (!added) {
                neighbors.add(new Point(x, y));
            }
        }
        return neighbors;
    }

    private int findAvailableX(int y) {
        for (int i = grid.length - 1; i >= 0; i--) {
            if (grid[i][y] == 0) {
                return i;
            }
        }
        return -1;
    }
}
