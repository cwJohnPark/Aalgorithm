package programmers.openchat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class OpenChat {

	Users users = new Users();

	public String[] solution(String[] record) {
		Records records = toRecords(record);
		return records.toResult(users);
	}

	private Records toRecords(String[] record) {
		return new Records(
			Arrays.stream(record)
				.map(this::toRecord)
				.collect(Collectors.toList()));
	}

	private Record toRecord(String line) {
		String[] splitRecord = line.split("\\W");
		Command command = Command.valueOf(splitRecord[0]);
		String userId = splitRecord[1];
		String nickName = splitRecord.length > 2 ? splitRecord[2] : "";

		users.updateNickName(userId, nickName);

		return new Record(command, userId);
	}

	public static class Record {
		Command command;
		String uid;

		public Record(Command command, String uid) {
			this.command = command;
			this.uid = uid;
		}

		public boolean isShowable() {
			return command.isShowable();
		}
	}

	static class Records {
		List<Record> recordList;
		String resultLine = "%s님이 %s";

		public Records(List<Record> recordList) {
			this.recordList = recordList;
		}

		public String[] toResult(Users users) {
			return recordList.stream()
				.filter(Record::isShowable)
				.map(record ->
					String.format(resultLine, users.getUserNickname(record.uid), record.command.kor))
				.toArray(String[]::new);
		}
	}

	enum Command {
		Enter("들어왔습니다."),
		Leave("나갔습니다."),
		Change("")
		;
		String kor;

		Command(String kor) {
			this.kor = kor;
		}

		public boolean isShowable() {
			return this != Change;
		}
	}

	static class Users {
		Map<String, User> users = new HashMap<>();

		public void updateNickName(String uid, String nickName) {
			if (Objects.nonNull(nickName) && !nickName.isEmpty()) {
				users.put(uid, new User(uid, nickName));
			}
		}

		public String getUserNickname(String uid) {
			return users.get(uid).nickName;
		}
	}

	static class User {
		String uid;
		String nickName;

		public User(String uid, String nickName) {
			this.uid = uid;
			this.nickName = nickName;
		}


		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			User user = (User)o;
			return Objects.equals(uid, user.uid);
		}

		@Override
		public int hashCode() {
			return Objects.hash(uid);
		}

		@Override
		public String toString() {
			return "User{" +
				"uid='" + uid + '\'' +
				", nickName='" + nickName + '\'' +
				'}';
		}
	}
}
