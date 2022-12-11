package programmers.hindex;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HIndexTest {

	HIndex hIndex;

	@BeforeEach
	void setUp() {
		hIndex = new HIndex();
	}

	@Test
	void solution1() {
		assertThat(hIndex.solution(new int[] {3, 0, 6, 1, 5})).isEqualTo(3);
		assertThat(hIndex.solution(new int[] {0})).isEqualTo(0);
		assertThat(hIndex.solution(new int[] {1})).isEqualTo(1);
		assertThat(hIndex.solution(new int[] {1,2})).isEqualTo(1);
		assertThat(hIndex.solution(new int[] {4,4,4,4})).isEqualTo(4);
		assertThat(hIndex.solution(new int[] {3,3,3,3})).isEqualTo(3);
		assertThat(hIndex.solution(new int[] {0,0,0,0})).isEqualTo(0);
	}
}
