package tasks;

import common.Person;
import common.PersonService;

import java.util.*;
import java.util.stream.Collectors;

/*
Задача 1
Метод на входе принимает List<Integer> id людей, ходит за ними в сервис
(он выдает несортированный Set<Person>, внутренняя работа сервиса неизвестна)
нужно их отсортировать в том же порядке, что и переданные id.
Оценить асимпотику работы
Асиптотика = O(n)
 */
public class Task1 {

  private final PersonService personService;

  public Task1(PersonService personService) {
    this.personService = personService;
  }

  public List<Person> findOrderedPersons(List<Integer> personIds) {
    Set<Person> persons = personService.findPersons(personIds);
    Map<Integer, Person> idEntityMapping = persons.stream()
            .collect(Collectors.toMap(Person::getId, person -> person));

    return personIds.stream()
            .map(personId -> idEntityMapping.get(personId))
            .filter(Objects::nonNull)
            .toList();
  }
}
