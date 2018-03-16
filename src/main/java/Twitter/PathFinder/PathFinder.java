package Twitter.PathFinder;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class PathFinder {
    int[][] map = {{1, 1, 0, 0, 0},
                   {1, 0, 1, 1, 1},
                   {1, 0, 1, 0, 1},
                   {1, 1, 1, 0, 1},
                   {0, 0, 0, 1, 1}};
    int X = map.length;
    int Y = map[0].length;

    int[][] moves = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) {
        PathFinder finder = new PathFinder();
        finder.find();
    }

    public void find() {
        Set<Point> visited = new HashSet<>();
        visited.add(new Point(0, 0));
        Map<Point, Point> pathMap = new HashMap<>();
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0));

        while(!queue.isEmpty()) {
            Point p = queue.poll();
            for (int[] move : moves) {
                int moveX = move[0];
                int moveY = move[1];

                int ex = p.x + moveX;
                int ey = p.y + moveY;
                if (ex >= 0 && ex < map.length && ey >= 0 && ey < map[0].length && map[ex][ey] == 1) {
                    if (!visited.contains(new Point(ex, ey))) {
                        pathMap.put(new Point(ex, ey), new Point(p.x, p.y));
                        queue.add(new Point(ex, ey));
                        visited.add(new Point(ex, ey));
                    }
                }
            }
        }

        Point endPoint = new Point(0, 0);
        Point curPoint = new Point(X - 1, Y - 1);
        Stack<Point> pathStack = new Stack<>();
        pathStack.push(curPoint);
        while(!endPoint.equals(curPoint)) {
            if (pathMap.containsKey(curPoint)) {
                curPoint = pathMap.get(curPoint);
                pathStack.push(curPoint);
            } else {
                throw new RuntimeException("Path Not Found");
            }
        }

        while(!pathStack.isEmpty()) {
            System.out.println(pathStack.pop());
        }
    }
}
