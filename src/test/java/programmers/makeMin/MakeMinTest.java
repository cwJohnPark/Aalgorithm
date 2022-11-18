package programmers.makeMin;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * [1, 4, 2]	[5, 4, 4]	29
 * [1,2]	[3,4]	10
 */
class MakeMinTest {

    MakeMin makeMin;


    @Test
    void makeMin() {
        int[] A = {1,4,2};
        int[] B = {5,4,4};
        makeMin = new MakeMin();

        int solution = makeMin.solution(A, B);
        assertThat(solution).isEqualTo(29);
    }

    @Test
    void sort() {
        int[] A = {1,4,2,3};
        Integer[] B = {1,3,5,4};

        Arrays.sort(A);
        Arrays.sort(B, Collections.reverseOrder());
        assertThat(A).containsExactly(1,2,3,4);
        assertThat(B).containsExactly(5,4,3,1);
    }
}