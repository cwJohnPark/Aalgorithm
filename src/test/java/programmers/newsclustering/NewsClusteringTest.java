package programmers.newsclustering;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class NewsClusteringTest {

    NewsClustering nc = new NewsClustering();

    @ParameterizedTest
    @CsvSource({"FRANCE,french,16384",
            "handshake, shake hands, 65536",
            "aa1+aa2, AAAA12, 43690",
            "E=M*C^2, e=m*c^2, 65536"
    })
    void solution(String str1, String str2, int ans) {
        assertThat(nc.solution(str1, str2)).isEqualTo(ans);
    }


    @Test
    void testCreateChunks() {
        assertThat(NewsClustering.split("handshake"))
                .extracting(NewsClustering.Chunk::toString)
                .containsExactly("ha","an","nd","ds","sh","ha","ak","ke");
        assertThat(NewsClustering.split("aa1+aa2"))
                .extracting(NewsClustering.Chunk::toString)
                .containsExactly("aa","aa");
        assertThat(NewsClustering.split("E=M*C^2"))
                .extracting(NewsClustering.Chunk::toString)
                .isEmpty();
    }

}