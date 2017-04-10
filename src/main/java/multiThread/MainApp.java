package multiThread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import multiThread.GoSequence.Point;

public class MainApp {
	
	private static Integer x = 2;
	private static Integer y = 3;
	
	private static final Integer M = 5;
	private static final Integer N = 6;
	
	private static int[][] matrix = new int[M][N];
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		List<Point> ret = new LinkedList<Point>();
		
		int iter = getMaxIter();
//		System.out.println(iter);
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		List<Future<List<Point>>> futureList = new LinkedList<Future<List<Point>>>();
		for (int i = 1; i <= iter; i++) {
			Future<List<Point>> future = 
					executorService.submit(new MapRunner(i, x - i + 1, y + i, M, N));
			futureList.add(future);
		}
		ret.add(new Point(x, y));
		for(Future<List<Point>> f: futureList) {
			try {
				ret.addAll(f.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		int num = 1;
		for(Point p: ret) {
			matrix[p.x][p.y] = num++;
		}
		
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				System.out.format("%5d", matrix[i][j]);
			}
			System.out.println();
		}
		
		executorService.shutdown();
		
		long endTime = System.currentTimeMillis();
		System.out.println("Execution Time:" + (endTime-startTime) + "ms");
	}

	private static int getMaxIter() {
		int maxY = Math.max(y, N - y - 1);
		int maxX = Math.max(x, M - x - 1);
		return Math.max(maxX, maxY);
	}

}
