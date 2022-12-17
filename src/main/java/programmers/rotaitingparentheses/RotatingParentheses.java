package programmers.rotaitingparentheses;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class RotatingParentheses {

	char[] opens = new char[] {'[', '{', '('};
	char[] closes = new char[] {']', '}', ')'};

	public int solution(String s) {
		int N = s.length();
		int totalCorrect = 0;
		for (int i = 0; i < N; i++) {
			totalCorrect += isCorrect(i, s) ? 1 : 0;
		}
		return totalCorrect;
	}

	public boolean isCorrect(int startIndex, String s) {
		return isCorrect(startIndex, s.toCharArray());
	}

	public boolean isCorrect(int startIndex, char[] parenthesesChar) {
		List<Character> parentheses = arrange(startIndex, parenthesesChar);
		return isCorrect(parentheses);
	}

	public boolean isCorrect(List<Character> parentheses) {
		Deque<Character> deque = new ArrayDeque<>();
		for (Character paren : parentheses) {
			if (deque.isEmpty()) {
				deque.add(paren);
				continue;
			}
			if (isOpen(paren)) {
				deque.add(paren);
				continue;
			}
			Character prevParen = deque.pollLast();
			if (!isOpenClose(prevParen, paren)) {
				return false;
			}
		}
		// check remaining
		while (!deque.isEmpty()) {
			Character open = deque.pollFirst();
			if (deque.isEmpty()) {
				return false;
			}
			Character close = deque.pollFirst();
			if (!isOpenClose(open, close)) {
				return false;
			}
		}

		return true;
	}

	private boolean isOpenClose(Character open, Character close) {
		return (isOpen(open) && isClose(close))&& findOpens(open) == findCloses(close);
	}

	private int findCloses(Character close) {
		return findIndex(close, closes);
	}

	private int findOpens(Character open) {
		return findIndex(open, opens);
	}

	private int findIndex(Character findChar, char[] candidates) {
		for (int i = 0; i < candidates.length; i++) {
			if (candidates[i] == findChar) {
				return i;
			}
		}
		throw new IllegalArgumentException();
	}

	public List<Character> arrange(int startIndex, char[] parentheses) {
		int L = parentheses.length;
		List<Character> deque = new ArrayList<>();
		for (int i = 0; i < L; i++) {
			int index = getIndex(startIndex, i, L);
			char p = parentheses[index];
			deque.add(p);
		}
		return deque;
	}

	public int getIndex(int startIndex, int i, int length) {
		return (i+startIndex) % length;
	}

	public boolean isOpen(String s) {
		return isOpen(s.toCharArray()[0]);
	}

	private boolean isClose(char other) {
		return !isOpen(other);
	}

	private boolean isOpen(char other) {
		for (char c : opens) {
			if (c == other) {
				return true;
			}
		}
		return false;
	}
}
