package multiThread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

import multiThread.GoSequence.Point;

public class MapRunner implements Callable<List<Point>>{
	int M;
	int N;
	int iter;
	int x;
	int y;
	List<Point> l = new LinkedList<Point>();
	
	public MapRunner(int iter, int x, int y, int M, int N) {
		this.iter = iter;
		this.x = x;
		this.y = y;
		this.M = M;
		this.N = N;
	}

	public List<Point> call() throws Exception {
		if (validXY(x, y)) {
			l.add(new Point(x, y));
		}
		goDownByIter(iter * 2 - 1);
		goLeftByIter(iter * 2);
		goUpByIter(iter * 2);
		goRightByIter(iter * 2);
		return l;
	}
	
	private void goUpByIter(int i) {
		while(i-- > 0) {
			x--;
			if (validXY(x, y)) {
				l.add(new Point(x, y));
			}
		}
		
	}

	private void goRightByIter(int i) {
		while(i-- > 0) {
			y++;
			if (validXY(x, y)) {
				l.add(new Point(x, y));
			}
		}
		
	}

	private void goLeftByIter(int i) {
		while(i-- > 0) {
			y--;
			if (validXY(x, y)) {
				l.add(new Point(x, y));
			}
		}
	}

	private void goDownByIter(int i) {
		while(i-- > 0) {
			x++;
			if (validXY(x, y)) {
				l.add(new Point(x, y));
			}
		}
	}

	private boolean validXY(int x, int y) {
		if (x < M && x >= 0 && y < N && y >= 0) {
			return true;
		}
		return false;
	}

}
