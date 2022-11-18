package programmers.endtoend;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EndToEnd {

    public static void main(String[] args) {
        EndToEnd e = new EndToEnd();
        int[] ans;

        ans = e.solution(3,
                new String[] {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"});
        System.out.println(Arrays.toString(ans));// [3,3]

        ans = e.solution(5,
            new String[] {"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"});
        System.out.println(Arrays.toString(ans)); // [0, 0]

        ans = e.solution(2,
                new String[] {"hello", "one", "even", "never", "now", "world", "draw"});
        System.out.println(Arrays.toString(ans)); // [1,3]
    }

    public int[] solution(int n, String[] words) {
        Users users = Users.of(n);

        User user = users.findFailer(words);
    }

    static class Users {
        List<User> participantsList;

        public Users(List<User> participantsList) {
            this.participantsList = participantsList;
        }

        public static Users of(int n) {
            return new Users(
                    IntStream.range(0, n)
                        .mapToObj(User::new)
                        .collect(Collectors.toList()));

        }

        public User findFailer(String[] words) {
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                int participantOrder = participantsList.size() % i;
                User user = participantsList.get(participantOrder);
                user.addWords(word);
                if (user.isFailed()) {
                    return user;
                }
            }
            return User.NONE;
        }
    }

    static class User {
        static final User NONE = new User(0);
        int order;
        Set<String> words = new HashSet<>();

        public User(int order) {
            this.order = order;
        }

        public void addWords(String word) {
            words.add(word);
        }

        public boolean isFailed() {
            words
        }
    }
}
