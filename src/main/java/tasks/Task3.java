package tasks;

import common.Person;
import java.util.List;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/*
Задача 3
Отсортировать коллекцию сначала по фамилии, по имени (при равной фамилии), и по дате создания (при равных фамилии и имени)
 */
public class Task3 {

  public static List<Person> sort(Collection<Person> persons) {
    if (persons == null) {
      return Collections.emptyList();
    }
    return persons.stream().sorted(
            Comparator.comparing(Person::getSecondName, Comparator.nullsFirst(Comparator.naturalOrder()))
                      .thenComparing(Person::getFirstName, Comparator.nullsFirst(Comparator.naturalOrder()))
                      .thenComparing(Person::getCreatedAt, Comparator.nullsFirst(Comparator.naturalOrder())))
            .toList();
  }
}
