package tasks;

import common.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task8Test {

    private Task8 task;

    @BeforeEach
    void before() {
        task = new Task8();
    }
    @Test
    void firstNamesTest1() {
        List<Person> persons = List.of(
            new Person(1, "Михил", Instant.now()),
            new Person(2, "Илья", Instant.now()));

        assertEquals(List.of("Илья"), task.getFirstNames(persons));
    }

    @Test
    void firstNamesTest2() {
        assertEquals(Collections.emptyList(), task.getFirstNames(null));
    }
    @Test
    void getDifferentNamesTest1() {
        List<Person> persons = List.of(
                new Person(1, "Михил", Instant.now()),
                new Person(2, "Илья", Instant.now()),
                new Person(3, "Илья", Instant.now()));

        assertEquals(Set.of("Илья"), task.getDifferentNames(persons));
    }

    @Test
    void getFullNameTest1() {
        Person person = new Person(1, "Илья", "Крюк", "Константинович", Instant.now());
        assertEquals("Крюк Илья Константинович", task.getFullName(person));
    }

    @Test
    void getFullNameTest2() {
        assertEquals("", task.getFullName(null));
    }

    @Test
    void getFullNameTest3() {
        Person person = new Person(1, "Илья", "Крюк", "", Instant.now());
        assertEquals("-Крюк Илья-", "-" + task.getFullName(person) + "-");
    }

    @Test
    void getFullNameTest4() {
        Person person = new Person(1, "Илья", null, "Константинович", Instant.now());
        assertEquals("-Илья Константинович-", "-" + task.getFullName(person) + "-");
    }

    @Test
    void getPersonFullNamesTest1() {
        List<Person> persons = List.of(
                new Person(1, "Илья", "Крюк", "Константинович", Instant.now()),
                new Person(2, "Михаил", "Дроздов", "Михайлович", Instant.now()));
        Map<Integer, String> personFullNames = Map.of(
                1, "Крюк Илья Константинович",
                2, "Дроздов Михаил Михайлович");
        assertTrue(Objects.equals(personFullNames,task.getPersonFullNames(persons)));
    }

    @Test
    void getPersonFullNamesTest2() {
        Map<Integer, String> expectedMap = Collections.emptyMap();
        assertTrue(Objects.equals(expectedMap, task.getPersonFullNames(null)));
    }

    @Test
    void getPersonFullNamesTest3() {
        List<Person> persons = List.of(
                new Person(null, "Илья", "Крюк", "Константинович", Instant.now()),
                new Person(2, "Михаил", "Дроздов", "Михайлович", Instant.now()));
        Map<Integer, String> personFullNames = Map.of(
                2, "Дроздов Михаил Михайлович");
        assertTrue(Objects.equals(personFullNames,task.getPersonFullNames(persons)));
    }

    @Test
    void getPersonFullNamesTest4() {
        Instant createdAt = Instant.now();
        List<Person> persons = List.of(
                new Person(2, "Михаил", "Дроздов", "Михайлович", createdAt),
                new Person(2, "Михаил", "Дроздов", "Михайлович", createdAt));
        Map<Integer, String> personFullNames = Map.of(
                2, "Дроздов Михаил Михайлович");
        assertTrue(Objects.equals(personFullNames,task.getPersonFullNames(persons)));
    }

    @Test
    void hasSamePersonsTest() {
        Instant createdAt = Instant.now();
        List<Person> persons1 = List.of(
                new Person(1, "Илья", "Крюк", "Константинович", createdAt),
                new Person(2, "Михаил", "Дроздов", "Михайлович", createdAt));
        List<Person> persons2 = List.of(
                new Person(1, "Илья", "Крюк", "Константинович", createdAt),
                new Person(3, "Артур", "Дроздов", "Михайлович", createdAt));
        assertEquals(true, task.hasSamePersons(persons1, persons2));

    }

    @Test
    void hasSamePersonsTest1() {
        Instant createdAt = Instant.now();
        List<Person> persons1 = Arrays.asList(
                new Person(1, "Илья", "Крюк", "Константинович", createdAt),
                null);
        List<Person> persons2 = List.of(
            new Person(1, "Илья", "Крюк", "Константинович", createdAt),
            new Person(3, "Артур", "Дроздов", "Михайлович", createdAt));
        assertEquals(true, task.hasSamePersons(persons1, persons2));
        assertEquals(true, task.hasSamePersons(persons2, persons1));

    }

    @Test
    void hasSamePersonsTest2() {
        Instant createdAt = Instant.now();
        List<Person> persons1 = new ArrayList<>();
        persons1.add(null);
        List<Person> persons2 = new ArrayList<>();
        persons2.add(null);
        assertEquals(false, task.hasSamePersons(persons1, persons2));
        assertEquals(false, task.hasSamePersons(persons2, persons1));

    }

    @Test
    void countEvenTest1() {
        List<Integer> integers = List.of(1, 1, 1, 2);
        assertEquals(1, task.countEven(integers.stream()));
    }

    @Test
    void countEvenTest2() {
        List<Integer> integers = Collections.emptyList();
        assertEquals(0, task.countEven(integers.stream()));
    }

    @Test
    void countEvenTest3() {
        assertEquals(0, task.countEven(null));
    }

    @Test
    void countEvenTest4() {
        List<Integer> integers = Arrays.asList(1, 1, null, 2);
        assertEquals(1, task.countEven(integers.stream()));
    }

    //todo: we should find the better way to implement this test
    @Test
    void countEvenTest5() {
        int arrayCapacity = 10000000;
        List<Integer> integers =new ArrayList<>(arrayCapacity);
        IntStream.range(0,arrayCapacity).forEach(i -> integers.add(2));
        assertEquals(arrayCapacity, task.countEven(integers.parallelStream()));
    }
}
