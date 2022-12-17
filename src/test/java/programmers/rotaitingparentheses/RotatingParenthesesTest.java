package programmers.rotaitingparentheses;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class RotatingParenthesesTest {

	RotatingParentheses r = new RotatingParentheses();

	@Test
	void solution() {
		assertThat(r.solution("[](){}")).isEqualTo(3);
		assertThat(r.solution("}]()[{")).isEqualTo(2);
		assertThat(r.solution("[)(]")).isEqualTo(0);
		assertThat(r.solution("}}}")).isEqualTo(0);
	}

	@Test
	void isOpenClosed() {
		assertThat(r.isOpen("[")).isTrue();

	}

	@Test
	void isCorrect() {
		assertThat(r.isCorrect(0, 	"[](){}")).isTrue();
	}

	@Test
	void getIndex() {
		assertThat(r.getIndex(0, 0, 5)).isEqualTo(0);
		assertThat(r.getIndex(0, 4, 5)).isEqualTo(4);
		assertThat(r.getIndex(4, 0, 5)).isEqualTo(4);
		assertThat(r.getIndex(4, 1, 5)).isEqualTo(0);
		assertThat(r.getIndex(4, 2, 5)).isEqualTo(1);
		assertThat(r.getIndex(4, 3, 5)).isEqualTo(2);
	}

	/**
	 * 0	"[](){}"	O
	 * 1	"](){}["	X
	 * 2	"(){}[]"	O
	 * 3	"){}[]("	X
	 * 4	"{}[]()"	O
	 * 5	"}[](){"	X
	 */
	@Test
	void arrange() {
		assertThat(r.arrange(0, "[](){}".toCharArray()))
			.containsExactlyElementsOf(toCharList("[](){}"));
		assertThat(r.arrange(1, "[](){}".toCharArray()))
			.containsExactlyElementsOf(toCharList("](){}["));
		assertThat(r.arrange(2, "[](){}".toCharArray()))
			.containsExactlyElementsOf(toCharList("(){}[]"));
		assertThat(r.arrange(3, "[](){}".toCharArray()))
			.containsExactlyElementsOf(toCharList("){}[]("));
		assertThat(r.arrange(4, "[](){}".toCharArray()))
			.containsExactlyElementsOf(toCharList("{}[]()"));
		assertThat(r.arrange(5, "[](){}".toCharArray()))
			.containsExactlyElementsOf(toCharList("}[](){"));
	}

	private List<Character> toCharList(String s) {
		return s.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
	}
	@Test
	void isCorrect2() {
		assertThat(r.isCorrect(toCharList("[](){}"))).isTrue();
		assertThat(r.isCorrect(toCharList("](){}["))).isFalse();
		assertThat(r.isCorrect(toCharList("(){}[]"))).isTrue();
		assertThat(r.isCorrect(toCharList("){}[]("))).isFalse();
		assertThat(r.isCorrect(toCharList("{}[]()"))).isTrue();
		assertThat(r.isCorrect(toCharList("}[](){"))).isFalse();
	}
}
