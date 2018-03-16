package Yelp.Bipartite;

import java.util.Arrays;
import java.util.Stack;

public class BipartiteChecker {
    int[][] graph = {{0, 1, 0, 1, 0},
                     {1, 0, 1, 0, 0},
                     {0, 1, 0, 1, 0},
                     {1, 0, 1, 0, 0},
                     {0, 0, 0, 0, 0}};
    int N = graph.length;

    public static void main(String[] args) {
        BipartiteChecker bc = new BipartiteChecker();
        System.out.println(bc.isBipartite());
        System.out.println(bc.isBipartiteDFS());
    }

    public boolean isBipartiteDFS() {
        int[] colorArr = new int[N];
        Arrays.fill(colorArr, -1); // unvisited

        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        colorArr[0] = 0;

        for(int i = 0; i < N; i++) {
            if (colorArr[i] != -1) {
                continue;
            }

            while (!stack.isEmpty()) {
                int p = stack.pop();
                for (int j = 0; j < N; j++) {
                    if (graph[p][j] == 1) {
                        if (colorArr[p] == colorArr[j]) {
                            return false;
                        } else if(colorArr[j] == -1) {
                            colorArr[j] = 1 - colorArr[p];
                            stack.push(j);
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean isBipartite() {
        int[] colorArr = new int[N];
        Arrays.fill(colorArr, -1); // unvisited

        for (int i = 0; i < N; i++) {
            if (!isBipartite(colorArr, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean isBipartite(int[] colorArr, int i) {

        for (int j = 0; j < N && i != j; j++) {
            if (graph[i][j] == 1) {
                if (colorArr[i] != -1 && colorArr[i] != 1 - colorArr[j]) {
                    return false;
                } else if (colorArr[i] == -1) {
                    colorArr[i] = 1 - colorArr[j];
                }
            }
        }
        if (colorArr[i] == -1) {
            colorArr[i] = 0;
        }
        return true;
    }
}
