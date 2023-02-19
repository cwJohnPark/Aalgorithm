package programmers.newsclustering;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NewClusteringTest {

    NewClustering nc = new NewClustering();

    @ParameterizedTest
    @CsvSource({"FRANCE,french,16384",
            "handshake, shake hands, 65536",
            "aa1+aa2, AAAA12, 43690",
            "E=M*C^2, e=m*c^2, 65536"
    })
    void solution(String str1, String str2, int ans) {
        assertThat(nc.solution(str1, str2)).isEqualTo(ans);
    }
}