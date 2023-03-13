package programmers.openchat;


import static org.assertj.core.api.Assertions.*;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OpenChatTest {

	OpenChat openChat;

	@BeforeEach
	void setup() {
		openChat = new OpenChat();
	}

	@Test
	void solution() {
		String[] ans = openChat.solution(new String[] {
			"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"});
		assertThat(ans).containsExactly(
			"Prodo님이 들어왔습니다.", "Ryan님이 들어왔습니다.", "Prodo님이 나갔습니다.", "Prodo님이 들어왔습니다.");
	}
}