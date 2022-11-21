package programmers.endtoend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        Users users = Users.of(n, words);

        return users.findDropout();
    }

    static class Users {
        List<User> userList;
        final int totalCount;

        public Users(List<User> userList, int totalCount) {
            this.userList = userList;
            this.totalCount = totalCount;
        }

        public static Users of(int n, String[] words) {
            List<User> users = IntStream.range(0, n)
                    .mapToObj(User::new).collect(Collectors.toList());

            for (int i = 0; i < words.length; i++) {
                User user = users.get(i % n);
                user.addWord(words[i]);
            }
            return new Users(users, words.length);
        }

        public int[] findDropout() {
            int userCount = userList.size();
            int totalRound = totalCount / userCount;
            Words previousAllWords = new Words();
            for (int round = 0; round < totalRound; round++) {
                for (User user : userList) {
                    boolean isSuccess = user.matchRoundOf(round, previousAllWords);
                    if (!isSuccess) {
                        return new int[]{user.sequence+1, round+1};
                    }
                }
            }
            return new int[] {0,0};
        }

    }

    static class User {
        static final User NONE = new User(0);
        int sequence;
        Words words = new Words();

        public User(int sequence) {
            this.sequence = sequence;
        }

        public void addWord(String word) {
            words.add(word);
        }

        public List<String> getWordList() {
            return words.wordList;
        }

        public boolean matchRoundOf(int round, Words previousWords) {
            String word = words.getRoundOf(round);
            boolean isMatched = previousWords.match(word);
            previousWords.add(word);
            return isMatched;
        }
    }

    static class Words {
        List<String> wordList = new ArrayList<>();

        public Words() {
        }

        public Words(List<String> wordList) {
            this.wordList = wordList;
        }

        public static Words of(String ...words) {
            return new Words(Arrays.stream(words).collect(Collectors.toList()));
        }

        public boolean match(String word) {
            if (wordList.isEmpty()) {
                return true;
            }
            return isEndToEnd(word) && isNotRedundant(word);
        }

        private boolean isNotRedundant(String word) {
            return !wordList.contains(word);
        }

        public boolean isEndToEnd(String word) {
            String lastWord = getLastWord();
            return lastWord.substring(lastWord.length() - 1).equals(word.substring(0, 1));
        }

        private String getLastWord() {
            return wordList.get(wordList.size() - 1);
        }

        public void add(String word) {
            wordList.add(word);
        }

        public String getRoundOf(int round) {
            return wordList.get(round);
        }
    }
}
