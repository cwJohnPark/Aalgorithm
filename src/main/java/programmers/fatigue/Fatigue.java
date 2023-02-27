package programmers.fatigue;

public class Fatigue {

	int[][] dungeons;
	boolean[] visited;

	public int solution(int k, int[][] dungeons) {
		this.dungeons = dungeons;
		visited = new boolean[dungeons.length];
		return find(k, 0, 0);
	}

	public int find(int remain, int count, int max) {
		for (int i = 0; i < dungeons.length; i++) {
			int required = dungeons[i][0];
			int consumption = dungeons[i][1];
			if (visited[i] || (remain < required)) {
				continue;
			}
			visited[i] = true;
			max = find(remain - consumption, count+1, max);
			visited[i] = false;
		}
		return Math.max(max, count);
	}
}
