package programmers.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


class CacheTest {

    Cache cache;

    @Test
    void testCache() {
        int cacheSize = 3;
        Cache.LRUCache lruCache = new Cache.LRUCache(cacheSize);
        assertThat(lruCache.put("a")).isFalse();
        assertThat(lruCache.put("a")).isTrue();
        assertThat(lruCache.put("b")).isFalse();
        assertThat(lruCache.put("c")).isFalse();
        assertThat(lruCache.put("d")).isFalse();
        assertThat(lruCache.put("a")).isFalse();
    }

    /**
     * 3, ["Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"]
     * 1) 5, Jeju
     * 2) 5, Jeju, Pangyo
     * 3) 5, Jeju, Pangyo, Seoul
     * 4) 5, NewYork, Pangyo, Seoul
     * 5) 5, NewYork, LA, Seoul
     * 6) 5, NewYork, LA, Jeju
     * 7) 5, Pangyo, LA, Jeju
     * 8) 5, Pangyo, Seoul, Jeju
     * 9) 5, Pangyo, Seoul, NewYork
     * 10) 5, LA, Seoul, NewYork
     * =>50
     */
    /**
     * 3, ["Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"]
     * 1) 5, [Jeju]
     * 2) 5, Jeju, [Pangyo]
     * 3) 5, Jeju, Pangyo, [Seoul]
     * 4) 1, [Jeju], Pangyo, Seoul
     * 5) 1, Jeju, [Pangyo], Seoul
     * 6) 1, Jeju, Pangyo, [Seoul]
     * 6) 1, [Jeju], Pangyo, Seoul
     * 7) 1, Jeju, [Pangyo], Seoul
     * 7) 1, Jeju, Pangyo, [Seoul]
     * =>21
     */
    /**
     * 2, ["Jeju", "Pangyo", "NewYork", "newyork"]
     * 1) 5, [Jeju]
     * 2) 5, Jeju, [Pangyo]
     * 3) 5, [NewYork], Pangyo
     * 4) 1, [NewYork], Pangyo
     * =>16
     */
    static Stream<Arguments> provideSolution() {
        return Stream.of(
                Arguments.of(3, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"}, 50),
                Arguments.of(3, new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"}, 21),
                Arguments.of(2, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"}, 60),
                Arguments.of(2, new String[]{"Jeju", "Pangyo", "NewYork", "newyork"}, 16),
                Arguments.of(5, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"}, 52),
                Arguments.of(0, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA"}, 25)
        );
    }

    @BeforeEach
    void setUp() {
        cache = new Cache();
    }

    @ParameterizedTest
    @MethodSource("provideSolution")
    void solution1(int cacheSize, String[] cities, int expectedExecutionTime) {
        assertThat(cache.solution(cacheSize, cities)).isEqualTo(expectedExecutionTime);
    }


}
