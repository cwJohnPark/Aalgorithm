package programmers.coloringbook;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ColoringBookTest {

	ColoringBook c = new ColoringBook();

	@Test
	void solution1() {
		int[] ans = c.solution(6, 4, new int [][] {
				{1, 1, 1, 0},
				{1, 2, 2, 0},
				{1, 0, 0, 1},
				{0, 0, 0, 1},
				{0, 0, 0, 3},
				{0, 0, 0, 3}});

		assertThat(ans).containsExactly(4, 5);
	}

	@Test
	void solution2() {
		int[] ans = c.solution(6, 4, new int [][] {
				{1, 1, 1, 0},
				{1, 2, 2, 0},
				{0, 4, 0, 1},
				{0, 4, 4, 1},
				{4, 4, 4, 3},
				{4, 3, 3, 3}});

		assertThat(ans).containsExactly(5, 7);
	}

	@Test
	void solution3() {
		int[] ans = c.solution(5, 5, new int [][] {
			{0, 1, 1, 3, 1},
			{0, 1, 1, 3, 1},
			{0, 0, 0, 0, 0},
			{0, 1, 1, 2, 4},
			{0, 1, 1, 2, 4}
		});

		assertThat(ans).containsExactly(6, 4);
	}

	@Test
	void solution4() {
		int[] ans = c.solution(13, 16,
			new int [][] {
				{0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
				{0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
				{0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
				{0, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 0},
				{0, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 1, 1, 0},
				{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
				{0, 1, 3, 3, 3, 1, 1, 1, 1, 1, 1, 3, 3, 3, 1, 0},
				{0, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 0},
				{0, 0, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 0, 0},
				{0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
				{0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}}
		);

		assertThat(ans).containsExactly(12, 120);
	}

	@Test
	void testMatrix() {
		ColoringBook.Matrix matrix = new ColoringBook.Matrix(6, 4, new int[][] {
			{1, 1, 1, 0},
			{1, 2, 2, 0},
			{1, 0, 0, 1},
			{0, 0, 0, 1},
			{0, 0, 0, 3},
			{0, 0, 0, 3}});

		int max = matrix.find(1, 0, 0, 0);
		assertThat(max).isEqualTo(5);
	}

	@Test
	void testMatrix2() {
		ColoringBook.Matrix matrix = new ColoringBook.Matrix(6, 4, new int[][] {
			{1, 1, 1, 0},
			{1, 2, 2, 0},
			{1, 1, 1, 1},
			{0, 0, 1, 1},
			{0, 0, 1, 3},
			{0, 0, 3, 3}});

		int max = matrix.findLargest(3);
		assertThat(max).isEqualTo(3);
	}

	@Test
	void testMatrix3() {
		ColoringBook.Matrix matrix = new ColoringBook.Matrix(4, 4, new int[][] {
			{1, 1, 1, 0},
			{1, 2, 2, 0},
			{1, 1, 1, 1},
			{0, 0, 1, 1}
		});

		int max = matrix.findLargest(1);
		assertThat(max).isEqualTo(10);
	}

	@Test
	void testMatrix4() {
		ColoringBook.Matrix matrix = new ColoringBook.Matrix(4, 4, new int[][] {
			{0, 1, 1, 0},
			{0, 1, 1, 0},
			{1, 1, 0, 1},
			{0, 1, 1, 0}
		});

		int max = matrix.findLargest(1);
		assertThat(max).isEqualTo(8);
	}

}
