package programmers.takbae;



import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TakBaeTest {

	TakBae takBae;

	@BeforeEach
	void setUp() {
		takBae = new TakBae();
	}

	@Test
	void solution() {
		int ans;
		ans = takBae.solution(new int[] {4, 3, 1, 2, 5});
		Assertions.assertThat(ans).isEqualTo(2);

		ans = takBae.solution(new int[] {5, 4, 3, 2, 1});
		Assertions.assertThat(ans).isEqualTo(5);
	}
}