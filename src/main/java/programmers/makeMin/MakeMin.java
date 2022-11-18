package programmers.makeMin;

import java.util.Arrays;

public class MakeMin {
    public int solution(int []A, int []B) {
        int length = A.length;
        Arrays.sort(A);
        Arrays.sort(B);

        int total = 0;
        for(int i = 0; i < length; i++) {
            total += A[i] * B[length-1-i];
        }

        return total;
    }
}
