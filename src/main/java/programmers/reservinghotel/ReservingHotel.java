package programmers.reservinghotel;

import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReservingHotel {

	public int solution(String[][] book_time) {
		Times times = new Times();
		times = times.addTimes(book_time);

		System.out.println(times.timeCountMap);
		return times.getMaxCount();
	}

	public static class Times {
		static final int OFFSET_TIME_MINUTE = 10;
		static final LocalTime MAX_TIME = LocalTime.of(23, 59);

		final Map<LocalTime, Integer> timeCountMap;

		public Times() {
			timeCountMap = new LinkedHashMap<>(24*24);
		}

		public Times addTimes(String[][] times) {
			for (String[] time : times) {
				addTime(time);
			}
			return this;
		}

		private void addTime(String[] time) {
			addTime(parseToLocalTime(time[0]), parseToLocalTime(time[1]));
		}

		private void addTime(LocalTime from, LocalTime to) {
			LocalTime until = from;
			LocalTime toOffset = addOffset(to);

			while (until.isBefore(toOffset)) {
				timeCountMap.merge(until, 1, Integer::sum);
				until = until.plusMinutes(1);
			}
		}

		// 23:59 + 00:10 = 00:09
		private LocalTime addOffset(LocalTime to) {
			LocalTime toOffset = to.plusMinutes(OFFSET_TIME_MINUTE);
			return toOffset.isBefore(to) ? MAX_TIME : toOffset;
		}

		private LocalTime parseToLocalTime(String time) {
			String[] split = time.split(":");
			return LocalTime.of(Integer.parseInt(split[0]),
				Integer.parseInt(split[1]));
		}

		public int getMaxCount() {
			return timeCountMap.values().stream()
				.mapToInt(Integer::intValue)
				.max()
				.orElse(0);
		}

		@Override
		public String toString() {
			return "Times{" +
				"timeCountMap=" + timeCountMap +
				'}';
		}
	}
}
