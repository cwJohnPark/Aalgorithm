package programmers.lieftboat;

import java.util.Arrays;

public class LifeBoatProblemFast {

    public int solution(int[] people, int limit) {
        int count = 0;
        Arrays.sort(people);
        int LENGTH = people.length;
        int minWeight = 0;
        int maxWeight = LENGTH -1;
        while (minWeight < maxWeight) {
            // 가장 무거운 + 가장 가벼운 1명씩 태운다
            if (people[minWeight] + people[maxWeight] <= limit) {
                minWeight++;
                maxWeight--;
            } else {
                // 가장 무거운 1명만 태운다
                maxWeight--;
            }
            count++;
        }

        return minWeight == maxWeight ? count + 1 : count;
    }
}
