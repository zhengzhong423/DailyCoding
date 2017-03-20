package Graph.matrix01;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {
	
	static Solution sol = new Solution();

	public static void main(String[] args) {
		List<Integer> row0 = new LinkedList<Integer>();
		row0.addAll(Arrays.asList(new Integer[] {0, 0, 0}));
		List<Integer> row1 = new LinkedList<Integer>();
		row1.addAll(Arrays.asList(new Integer[] {0, 1, 0}));
		List<Integer> row2 = new LinkedList<Integer>();
		row2.addAll(Arrays.asList(new Integer[] {1, 1, 1}));
		
		List<List<Integer>> matrix = new LinkedList<List<Integer>>();
		matrix.add(row0);
		matrix.add(row1);
		matrix.add(row2);
		
		System.out.println(sol.updateMatrix(matrix));
	}
	
	public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
		
		for(int i = 0; i < matrix.size(); i++) {
			for(int j = 0; j < matrix.get(0).size(); j++) {
				if (matrix.get(i).get(j) != 0) {
					matrix.get(i).set(j, Integer.MAX_VALUE - 1);
					if (i > 0 && matrix.get(i - 1).get(j) + 1 < matrix.get(i).get(j)) {
						matrix.get(i).set(j, matrix.get(i - 1).get(j) + 1);
					}
					if (j > 0 && matrix.get(i).get(j - 1) + 1 < matrix.get(i).get(j)) {
						matrix.get(i).set(j, matrix.get(i).get(j - 1) + 1);
					}
				}
			}
		}
		
		for (int i = matrix.size() - 1; i >= 0; i--) {
			for(int j = matrix.get(0).size() - 1; j >= 0; j--) {
				if (matrix.get(i).get(j) != 0) {
					if (i < matrix.size() - 1 && matrix.get(i + 1).get(j) + 1 < matrix.get(i).get(j)) {
						matrix.get(i).set(j, matrix.get(i + 1).get(j) + 1);
					}
					if (j < matrix.get(0).size() - 1 && matrix.get(i).get(j + 1) + 1 < matrix.get(i).get(j)) {
						matrix.get(i).set(j, matrix.get(i).get(j + 1) + 1);
					}
				}
			}
		}
        return matrix;
    }

}
