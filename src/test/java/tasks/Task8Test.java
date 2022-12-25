package tasks;

import common.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

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
    void hasSamePersonTest() {
        List<Person> persons1 = List.of(
                new Person(1, "Илья", "Крюк", "Константинович", Instant.now()),
                new Person(2, "Михаил", "Дроздов", "Михайлович", Instant.now()));
        List<Person> persons2 = List.of(
                new Person(1, "Илья", "Крюк", "Константинович", Instant.now()),
                new Person(3, "Артур", "Дроздов", "Михайлович", Instant.now()));
        assertEquals(true, task.hasSamePersons(persons1, persons2));

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
}
