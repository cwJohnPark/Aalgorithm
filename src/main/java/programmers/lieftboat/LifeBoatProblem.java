package programmers.lieftboat;

import java.util.*;
import java.util.stream.Collectors;

/**
 * url: https://school.programmers.co.kr/learn/courses/30/lessons/42885
 *
 */
public class LifeBoatProblem {

    public int solution(int[] people, int limit) {
        LifeBoat lifeBoat = new LifeBoat(limit, new People(people));
        return lifeBoat.savePeople();
    }

    static class LifeBoat {
        int limit;
        People people;
        int count = 0;

        public LifeBoat(int limit, People people) {
            this.limit = limit;
            this.people = people;
        }

        public void saveNextPersons() {
            People nextPersons = people.getPeopleUntilLimitWeight(limit);
            count ++;
            people.removeAll(nextPersons);
        }

        public int savePeople() {
            while (!people.isEmpty()) {
                saveNextPersons();
            }
            return count;
        }
    }

    static class Person {
        String id = UUID.randomUUID().toString();
        int weight;

        public Person(int weight) {
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return weight == person.weight && Objects.equals(id, person.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, weight);
        }
    }

    static class People {
        LinkedList<Person> personList;

        public People(List<Person> personList) {
            personList.sort(Comparator.comparing(Person::getWeight).reversed());
            this.personList = new LinkedList<>(personList);
        }

        public People(int ...weights) {
            this(Arrays.stream(weights).mapToObj(Person::new).collect(Collectors.toList()));
        }

        public People getPeopleUntilLimitWeight(int limit) {
            if (personList.size() == 1) {
                return this;
            }

            Person pivot = personList.get(0);
            int totalWeight = pivot.weight;

            List<Person> resultPersons = new ArrayList<>();
            resultPersons.add(pivot);

            for (int i = 1; i < personList.size(); i++) {
                if (resultPersons.size() > 1) {
                    return new People(resultPersons);
                }
                Person next = personList.get(i);

                if (limit >= totalWeight + next.weight) {
                    resultPersons.add(next);
                    totalWeight += next.weight;
                }
                if (totalWeight > limit) {
                    break;
                }
            }

            return new People(resultPersons);
        }

        public void removeAll(People others) {
            personList.removeAll(others.personList);
        }

        public boolean isEmpty() {
            return personList.isEmpty();
        }
    }
}
