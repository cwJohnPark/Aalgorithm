package programmers.cache;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class Cache {
    final int HIT_TIME = 1;
    final int MISS_TIME = 5;

    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) {
            return cities.length * 5;
        }
        int time = 0;
        LRUCache cache = new LRUCache(cacheSize);
        for (String city : cities) {
            if (cache.put(city)) {
                time += HIT_TIME;
            } else {
                time += MISS_TIME;
            }
        }

        return time;
    }

    static class Node<T> {
        T value;

        public Node(T value) {
            this.value = value;
        }
    }

    static class LRUCache {
        Set<String> hash;
        Deque<String> queue;
        int capacity;

        public LRUCache(int capacity) {
            if (capacity < 1) {
                throw new IllegalArgumentException("캐시 용량은 1보다 작을 수 없음");
            }
            this.capacity = capacity;
            this.hash = new HashSet<>(capacity);
            queue = new ArrayDeque<>(capacity);
        }

        public boolean put(String key) {
            key = key.toLowerCase();

            boolean isExist = false;
            if (hash.contains(key)) {
                // 캐시에 존재한다면 큐에서 일단 제거한다.
                // 나중에 큐의 처음에 이것을 집어넣는다.
                queue.remove(key);
                isExist = true;
            } else {
                // 큐에 넣을 용량이 없을 경우
                //// 큐의 마지막것이 낮은 우선수위이므로 큐의 마지막을 제거한다.
                ///// map 에서도 제거한다.
                if (queue.size() >= capacity) {
                    String temp = queue.removeLast();
                    hash.remove(temp);
                }
            }
            // 캐시 중 가장 최근의 것이 되어야 하므로, 큐의 처음에 넣는다.
            Node<String> newNode = new Node<>(key);
            queue.addFirst(newNode.value);
            hash.add(key);

            return isExist;
        }
    }
}
