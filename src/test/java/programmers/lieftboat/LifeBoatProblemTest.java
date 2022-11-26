package programmers.lieftboat;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static programmers.lieftboat.LifeBoatProblem.*;

class LifeBoatProblemTest {

    LifeBoatProblem lifeBoatProblem = new LifeBoatProblem();

    People people;
    int limit;

    @Test
    void solution() {
        int[] people = new int[] {70, 80, 50};
        int limit = 100;
        int ans = lifeBoatProblem.solution(people, limit);
        assertThat(ans).isEqualTo(3);
    }

    @Test
    void solution2() {
        int[] people = new int[] {70, 50, 80, 50};
        int limit = 100;
        int ans = lifeBoatProblem.solution(people, limit);
        assertThat(ans).isEqualTo(3);
    }

    @Test
    void solution3() {
        int[] people = new int[] {90, 80, 10, 10, 10};
        int limit = 100;
        int ans = lifeBoatProblem.solution(people, limit);
        assertThat(ans).isEqualTo(3);
    }

    @Test
    void 한명() {
        int[] people = new int[] {90};
        int limit = 100;
        int ans = lifeBoatProblem.solution(people, limit);
        assertThat(ans).isEqualTo(1);
    }

    @Test
    void 순서() {
        int[] people = new int[] {10,20,30,50,80};
        int limit = 100;
        int ans = lifeBoatProblem.solution(people, limit);
        assertThat(ans).isEqualTo(3);
    }


    @Test
    void 순서2() {
        int[] people = new int[] {10,20,30,50,80};
        int limit = 100;
        int ans = lifeBoatProblem.solution(people, limit);
        assertThat(ans).isEqualTo(3);
    }

    @Test
    void others() {
        assertThat(lifeBoatProblem.solution(new int[] {40, 40, 40, 40, 50}, 200))
                .isEqualTo(3);
        assertThat(lifeBoatProblem.solution(new int[] {40, 50, 60, 90}, 100))
                .isEqualTo(3);
        assertThat(lifeBoatProblem.solution(new int[] {30, 40, 50, 60}, 100))
                .isEqualTo(2);
        assertThat(lifeBoatProblem.solution(new int[] {}, 100))
                .isEqualTo(0);
        assertThat(lifeBoatProblem.solution(new int[] {100,100,100,100}, 400))
                .isEqualTo(2);
    }

    @Test
    void 후보선정_2명() {
        limit = 100;
        people = new People(90, 10, 80);
        LifeBoat lifeBoat = new LifeBoat(limit, people);

        lifeBoat.saveNextPersons();

        assertThat(people.personList)
                .extracting(Person::getWeight)
                .containsExactly(80);
    }

    @Test
    void 후보선정_1명() {
        limit = 100;
        people = new People(90, 30, 80);
        LifeBoat lifeBoat = new LifeBoat(limit, people);

        lifeBoat.saveNextPersons();

        assertThat(people.personList)
                .extracting(Person::getWeight)
                .containsExactly(80, 30);
    }

    @Test
    void 후보선정_3명() {
        limit = 100;
        people = new People(100, 30, 20, 10);
        LifeBoat lifeBoat = new LifeBoat(limit, people);

        lifeBoat.saveNextPersons();
        lifeBoat.saveNextPersons();

        assertThat(people.personList)
                .extracting(Person::getWeight)
                .containsExactly(10);
    }
}