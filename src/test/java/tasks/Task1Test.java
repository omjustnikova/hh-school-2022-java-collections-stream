package tasks;

import common.Person;
import common.PersonService;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class Task1Test {

  @InjectMocks
  private Task1 task;

  @Mock
  private PersonService personService;

  @ParameterizedTest
  @MethodSource("generateData")
  public void test(List<Integer> ids) {
    Set<Person> persons = ids.stream()
        .sorted()
        .map(id -> new Person(id, "firstName", "secondName", Instant.now()))
        .collect(Collectors.toSet());
    when(personService.findPersons(eq(ids)))
        .thenReturn(persons);
    assertEquals(ids, task.findOrderedPersons(ids).stream().map(Person::getId).collect(Collectors.toList()));
  }

  @ParameterizedTest
  @MethodSource("generateData")
  public void testMissingPerson(List<Integer> ids) {
    Set<Person> persons = ids.stream()
            .filter(person -> person != 1)
            .sorted()
            .map(id -> new Person(id, "firstName", "secondName", Instant.now()))
            .collect(Collectors.toSet());
    when(personService.findPersons(eq(ids)))
            .thenReturn(persons);
    assertEquals(ids.stream().filter(id -> id != 1).toList(), task.findOrderedPersons(ids).stream()
            .map(Person::getId)
            .filter(Objects::nonNull)
            .collect(Collectors.toList()));
  }

  @Test
  public void testNullIds() {
    assertEquals(Collections.emptyList(), task.findOrderedPersons(null));
  }

  private static Stream<Arguments> generateData() {
    return Stream.of(
        Arguments.of(List.of()),
        Arguments.of(List.of(3, 1, 2)),
        Arguments.of(List.of(1)),
        Arguments.of(List.of(5, 4, 3, 2, 1))
    );
  }
}
