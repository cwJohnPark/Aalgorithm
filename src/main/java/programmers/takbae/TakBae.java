package programmers.takbae;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TakBae {

	// 실제 순서, ContainerBelt = [1,2,3,4,5]
	// 트럭에 담아야할 박스 번호, Orders = [4,3,1,2,5]
	// [5,4,3,2,1]
	// [1,2,3,4,5]
	Deque<Integer> orders;
	Deque<Integer> containerBelt;
	Deque<Integer> secondBelt;

	public int solution(int[] order) {
		int totalLoadCount = 0;
		secondBelt = new ArrayDeque<>();
		orders = toOrder(order);
		containerBelt = toContainerBelt(order);

		while (!containerBelt.isEmpty() && !orders.isEmpty()) {
			int boxNumber = containerBelt.pop();
			if (canLoad(boxNumber)) {
				totalLoadCount++;
			} else {
				saveToSecondBelt(boxNumber);
			}
		}

		while (!secondBelt.isEmpty() && !orders.isEmpty() &&
			Objects.equals(secondBelt.removeFirst(), orders.removeFirst())) {
			totalLoadCount++;
		}
		return totalLoadCount;
	}

	private void saveToSecondBelt(int boxNumber) {
		secondBelt.push(boxNumber);
	}

	private boolean canLoad(int boxNumber) {
		int toLoadNumber = orders.removeFirst();
		if (!secondBelt.isEmpty() && secondBelt.getFirst() == toLoadNumber) {
			secondBelt.removeFirst();
			containerBelt.addFirst(boxNumber);
			return true;
		}
		if (toLoadNumber == boxNumber) {
			return true;
		}
		orders.addFirst(toLoadNumber);
		return false;
	}

	private Deque<Integer> toOrder(int[] order) {
		return Arrays.stream(order)
			.boxed()
			.collect(Collectors.toCollection(ArrayDeque::new));
	}

	private Deque<Integer> toContainerBelt(int[] orders) {
		return IntStream.range(1, orders.length+1)
			.boxed()
			.collect(Collectors.toCollection(ArrayDeque::new));
	}

}
