package programmers.newsclustering;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewsClustering {

    private static final int OFFSET = 65536;

    public int solution(String str1, String str2) {

        ChunkCount chunkCount1 = toChunkCount(str1);
        ChunkCount chunkCount2 = toChunkCount(str2);

        int intersection = chunkCount1.getIntersection(chunkCount2);
        int union = chunkCount1.getUnion(chunkCount2);

        if (union == 0 && intersection == 0) {
            return OFFSET;
        }
        return (int) (OFFSET * ((double) intersection / union));
    }

    private ChunkCount toChunkCount(String str) {
        return ChunkCount.to(split(str));
    }

    // 두 글자씩 끊는다.
    public static List<Chunk> split(String str) {
        char[] chars = str.toCharArray();
        List<Chunk> chunks = new ArrayList<>();
        for (int i = 0; i < chars.length-1; i++) {
            char char1 = chars[i];
            char char2 = chars[i + 1];
            if (!isValidChar(char1, char2)) {
                continue;
            }
            chunks.add(new Chunk(char1, char2));
        }
        return chunks;
    }

    private static boolean isValidChar(char char1, char char2) {
        return Character.isAlphabetic(char1) &&
                Character.isAlphabetic(char2);
    }

    public static class ChunkCount {
        final Map<Chunk, Integer> chunks;

        public ChunkCount(Map<Chunk, Integer> chunk) {
            this.chunks = chunk;
        }

        public static ChunkCount to(List<Chunk> chunks) {
            return new ChunkCount(chunks.stream()
                    .collect(Collectors.toMap(
                            Function.identity(),
                            it -> 1,
                            Integer::sum)));
        }

        public int getIntersection(ChunkCount other) {
            return chunks.entrySet()
                    .stream()
                    .filter(it -> other.contains(it.getKey()))
                    .map(it ->
                        Math.min(it.getValue(), other.getCount(it.getKey()))
                    )
                    .mapToInt(Integer::intValue)
                    .sum();
        }

        public int getUnion(ChunkCount other) {
            // merge to chunks
            return Stream.concat(
                    chunks.entrySet().stream(),
                    other.chunks.entrySet().stream()
            ).collect(
                    Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            Math::max
                            ))
                    .values()
                    .stream()
                    .mapToInt(Integer::intValue)
                    .sum();
        }

        private boolean contains(Chunk key) {
            return chunks.containsKey(key);
        }

        private int getCount(Chunk key) {
            return chunks.get(key);
        }
    }

    public static class Chunk {
        char ch1;
        char ch2;

        public Chunk(char ch1, char ch2) {
            this.ch1 = Character.toLowerCase(ch1);
            this.ch2 = Character.toLowerCase(ch2);
        }

        public String toString() {
            return ch1 + "" + ch2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Chunk chunk = (Chunk) o;
            return ch1 == chunk.ch1 && ch2 == chunk.ch2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(ch1, ch2);
        }
    }
}
