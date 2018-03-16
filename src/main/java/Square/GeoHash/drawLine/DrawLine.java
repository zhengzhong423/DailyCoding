package Square.GeoHash.drawLine;

import java.util.Arrays;

public class DrawLine {
    final int M = 10;
    final int N = 10;
    int[][] grid = new int[M][N];

    public static void main(String[] args) {
        DrawLine drawLine = new DrawLine();
        drawLine.drawLine(0, 0, 9, 8);
        for (int i = 0; i < drawLine.M; i++) {
            System.out.println(Arrays.toString(drawLine.grid[i]));
        }
    }

    void drawLine(int x1, int y1, int x2, int y2) {
        if (x1 == x2) {
            for (int i = y1; i <= y2; i++) {
                grid[x1][i] = 1;
            }
            return;
        }

        if (x2 < x1) {
            drawLine(x2, y2, x1, y1);
            return;
        }

        double slope = ((double)y2 - (double)y1) / ((double)x2 - (double)x1);
        double intercept = y1 - (double)x1 * slope;

        int prevY = y1;

        for (int i = x1; i <= x2; i++) {
            double y = slope * i + intercept;
            int curY = (int)Math.floor(y + 0.5d);
            System.out.println(curY);
            System.out.println(prevY);
            if (Math.abs(curY - prevY) > 1) {
                int small = Math.min(curY, prevY);
                int large = Math.max(curY, prevY);
                for (int j = small + 1; j < large; j++) {
                    grid[i][j] = 1;
                }
            }
            grid[i][curY] = 1;
            prevY = curY;
        }
    }
}
