package programmers.hindex;

public class HIndex {

	// 1 <= length <= 1_000
	// 0 <= citations[i] <= 10_000
	public int solution(int[] citations) {
		int HIndex = 0;
		int maxCount = 0;
		int citationCount = citations.length;

		while (citationCount > 0) {
			int count = 0;
			// 배열 갯수를 하나씩 줄여가며,
			// 인용횟수가 배열 갯수 이상이 몇개 있는지 계산
			for (int citation : citations) {
				count += citationCount <= citation ? 1 : 0;
			}
			// citationCount=인용횟수
			// count = 인용횟수 이상의 수의 갯수
			// 조건1: 인용 횟수이상의 수의 갯수(count)가 최대 인용 횟수 이상의 수의 갯수보다 커야 한다
			// 조건2: 인용횟수 이상의 수의 갯수(count)가 인용횟수(citationCount)이상이어야 한다
			// 조건3: 이전에 계산된 HIndex 보다 인용횟수가 커야 한다
			if (maxCount < count && count >= citationCount && citationCount > HIndex) {
				HIndex = citationCount;
				maxCount = count;
			}

			citationCount -= 1;
		}


		return HIndex;
	}
}
