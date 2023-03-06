package programmers.coloringbook;

import java.util.HashSet;
import java.util.Set;

public class ColoringBook {

	public int[] solution(int m, int n, int[][] picture) {
		Set<Integer> numbers = getNumbers(picture);
		return getMaxSizeOfOneArea(numbers, m, n, picture);
	}

	private int[] getMaxSizeOfOneArea(Set<Integer> seen, int m, int n, int[][] picture) {
		int max = 0;
		Matrix matrix = new Matrix(m, n, picture);
		for (Integer searchNumber : seen) {
			max = Math.max(max, matrix.findLargest(searchNumber));
		}
		return new int[] {matrix.totalArea, max};
	}

	private Set<Integer> getNumbers(int[][] pictures) {
		Set<Integer> seen = new HashSet<>();
		for (int[] row : pictures) {
			for (int number : row) {
				if (number == 0) {
					continue;
				}
				seen.add(number);
			}
		}
		return seen;
	}

	static class Matrix {
		final int[][] matrix;
		boolean[][] checked;
		final int m;
		final int n;
		int max;
		int totalArea;

		Matrix(int m, int n, int[][] matrix) {
			this.matrix = matrix;
			checked = new boolean[m][n];
			this.m = m;
			this.n = n;
		}

		public int findLargest(int searchNumber) {
			for (int y = 0; y < m; y++) {
				for (int x = 0; x < n; x++) {
					if (checked[y][x]) {
						continue;
					}
					int foundArea = find(searchNumber, y, x, 0);
					totalArea += foundArea > 0 ? 1 : 0;
					max = Math.max(max, foundArea);
				}
			}
			int res = max;
			max = 0;

			return res;
		}

		public int find(int searchNumber, int y, int x, int max) {
			if (y < 0 || x < 0 || y >= m || x >= n) {
				return max;
			}
			if (checked[y][x]) {
				return max;
			}

			if (matrix[y][x] != searchNumber) {
				return max;
			}
			checked[y][x] = true;
			max += 1;
			max = find(searchNumber, y-1, x, max);
			max = find(searchNumber, y, x+1, max);
			max = find(searchNumber, y+1, x, max);
			max = find(searchNumber, y, x-1, max);

			return max;
		}
	}
}
