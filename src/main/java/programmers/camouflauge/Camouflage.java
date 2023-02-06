package programmers.camouflauge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Camouflage {

	public static final int ONE = 1;

	public int solution2(String[][] clothes) {
		return Arrays.stream(clothes)
			.map( it -> it[1])
			.collect(Collectors.toMap(Function.identity(), it -> ONE, Integer::sum))
			.values()
			.stream()
			.reduce(ONE, (left, right) -> left * (right+1)) - ONE;
	}

	public int solution(String[][] clothes) {
		Map<String, Integer> map = toMap(clothes);
		return getCombination(map);
	}

	private int getCombination(Map<String, Integer> map) {
		return map.values().stream()
			.reduce(1, (left, right) -> left * (right+1));
	}

	private Map<String, Integer> toMap(String[][] clothes) {
		Map<String, Integer> map = new HashMap<>();
		for (String[] cloth : clothes) {
			map.merge(cloth[1], 1, Integer::sum);
		}
		return map;
	}
}
