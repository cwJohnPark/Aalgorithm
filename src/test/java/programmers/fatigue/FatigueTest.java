package programmers.fatigue;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FatigueTest {

	Fatigue fatigue = new Fatigue();

	@Test
	void solution() {
		int ans = fatigue.solution(80, new int[][]{{80,20},{50,40},{30,10}});
		assertThat(ans).isEqualTo(3);
	}

}