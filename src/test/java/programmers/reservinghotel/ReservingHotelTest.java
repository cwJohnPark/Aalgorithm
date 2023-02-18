package programmers.reservinghotel;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalTime;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReservingHotelTest {

	ReservingHotel r;

	@BeforeEach
	void setup() {
		r = new ReservingHotel();
	}

	@Test
	void solution() {
		int ans = r.solution(new String[][]{{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}});
		assertThat(ans).isEqualTo(3);
		ans = r.solution(new String[][]{{"09:10", "10:10"}, {"10:20", "12:20"}});
		assertThat(ans).isEqualTo(1);
		ans = r.solution(new String[][]{{"10:20", "12:30"}, {"10:20", "12:30"}, {"10:20", "12:30"}});
		assertThat(ans).isEqualTo(3);
	}

	@Test
	void solution2() {
		int ans = r.solution(
			new String[][]{{"00:00", "00:01"}, {"00:11", "23:59"}});
		assertThat(ans).isEqualTo(1);
	}

	@Test
	void solution3() {
		final String[][] times = new String[1000][2];
		IntStream.range(0, 1000)
			.forEach(i -> { times[i][0] = "00:00"; times[i][1] = "23:59";});
		int ans = r.solution(times);
		assertThat(ans).isEqualTo(1000);
	}

	@Test
	void toMap2() {
		ReservingHotel.Times times = new ReservingHotel.Times();
		times.addTimes(new String[][]{{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}});
		System.out.println(times.timeCountMap);
	}

	@Test
	void toMap() {
		ReservingHotel.Times times = new ReservingHotel.Times();
		times.addTimes(new String[][]{{"09:10", "10:10"}, {"10:20", "12:20"}});
		System.out.println(times.timeCountMap);
		assertThat(times.timeCountMap)
			.hasEntrySatisfying(LocalTime.of(9, 10), count -> assertThat(count).isEqualTo(1))
			.hasEntrySatisfying(LocalTime.of(9, 20), count -> assertThat(count).isEqualTo(1))
			.hasEntrySatisfying(LocalTime.of(10, 11), count -> assertThat(count).isEqualTo(1))
			.hasEntrySatisfying(LocalTime.of(10, 19), count -> assertThat(count).isEqualTo(1))
			.hasEntrySatisfying(LocalTime.of(10, 20), count -> assertThat(count).isEqualTo(1))
			.hasEntrySatisfying(LocalTime.of(12, 20), count -> assertThat(count).isEqualTo(1))
			;
	}
}
