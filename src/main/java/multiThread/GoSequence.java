package multiThread;

public class GoSequence {
	
	static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public String toString() {
			return "["+x+","+y+"] ";
		}
	}
	
	private static final Integer M = 4000;
	private static final Integer N = 2000;
	
	private static int[][] matrix = new int[M][N];
	
	private static Integer x = 1;
	private static Integer y = 4;
 	private static Integer num = 1;
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		matrix[x][y] = num++;
		int iter = 1;
		while(M * N >= num) {
			goRightByOne();
			goDownByIter(2 * iter - 1);
			goLeftByIter(2 * iter);
			goUpByIter(2 * iter);
			goRightByIter(2 * iter);
			iter++;
		}
		
//		for(int i = 0; i < M; i++) {
//			for(int j = 0; j < N; j++) {
//				System.out.format("%5d", matrix[i][j]);
//			}
//			System.out.println();
//		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("Execution Time:" + (endTime-startTime) + "ms");
		
	}

	private static void goUpByIter(int i) {
		while(i-- > 0) {
			x--;
			if (validXY(x, y)) {
				matrix[x][y] = num++;
			}
		}
		
	}

	private static void goRightByIter(int i) {
		while(i-- > 0) {
			y++;
			if (validXY(x, y)) {
				matrix[x][y] = num++;
			}
		}
		
	}

	private static void goLeftByIter(int i) {
		while(i-- > 0) {
			y--;
			if (validXY(x, y)) {
				matrix[x][y] = num++;
			}
		}
	}

	private static void goDownByIter(int i) {
		while(i-- > 0) {
			x++;
			if (validXY(x, y)) {
				matrix[x][y] = num++;
			}
		}
	}

	private static void goRightByOne() {
		y++;
		if (validXY(x, y)) {
			matrix[x][y] = num++;
		}
	}
	
	private static boolean validXY(int x, int y) {
		if (x < M && x >= 0 && y < N && y >= 0) {
			return true;
		}
		return false;
	}

}
