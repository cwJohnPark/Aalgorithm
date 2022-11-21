package programmers.endtoend;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EndToEndTest {

    int count1 = 3;
    String[] words1 = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};

    @Test
    void 끝말이_일치하지_않을_경우() {
        assertThat(EndToEnd.Words.of("tank").match("kick")).isTrue();
        assertThat(EndToEnd.Words.of("tank", "kick").match("mother")).isFalse();
    }

    @Test
    void 이미_나온_단어일경우() {
        assertThat(EndToEnd.Words.of("tank").match("kick")).isTrue();

        assertThat(EndToEnd.Words.of("kick")
                .match("kick")).isFalse();
    }

    @Test
    void 사용자와_단어생성() {
        EndToEnd.Users users = EndToEnd.Users.of(count1, words1);

        assertThat(users.userList.get(0).getWordList())
                .containsExactly("tank", "wheel", "mother");
        assertThat(users.userList.get(1).getWordList())
                .containsExactly("kick", "land", "robot");
        assertThat(users.userList.get(2).getWordList())
                .containsExactly("know", "dream", "tank");
    }

    @Test
    void 탈락자_판별() {
        EndToEnd.Users users = EndToEnd.Users.of(count1, words1);
        int[] dropout = users.findDropout();
        assertThat(dropout).containsExactly(3,3);
    }

    @Test
    void 탈락자_판별_모두_성공() {
        String[] words = {"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"};
        int n = 5;
        EndToEnd.Users users = EndToEnd.Users.of(n, words);
        int[] dropout = users.findDropout();
        assertThat(dropout).containsExactly(0,0);
    }


}