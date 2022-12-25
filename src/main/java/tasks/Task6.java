package tasks;

import common.Area;
import common.Person;

import java.util.*;
import java.util.stream.Collectors;

/*
Имеются
- коллекция персон Collection<Person>
- словарь Map<Integer, Set<Integer>>, сопоставляющий каждой персоне множество id регионов
- коллекция всех регионов Collection<Area>
На выходе хочется получить множество строк вида "Имя - регион". Если у персон регионов несколько, таких строк так же будет несколько
 */
public class Task6 {

  public static Set<String> getPersonDescriptions(Collection<Person> persons,
                                                  Map<Integer, Set<Integer>> personAreaIds,
                                                  Collection<Area> areas) {

    Map<Person, Set<String>> personsWithAreaNames = persons.stream()
            .collect(Collectors.toMap(person -> person,
                                      person -> personAreaIds.get(person.getId()).stream()
                                                      .map(id -> areas.stream().filter(area -> area.getId().equals(id))
                                                      .findAny().get().getName())
                                                      .collect(Collectors.toSet())));
    Map<Person, Set<String>> personWithNameAreaMapping = personsWithAreaNames.entrySet().stream()
            .collect(Collectors.toMap(e->e.getKey(),
                                      e -> e.getValue().stream()
                                              .map(areaName -> e.getKey().getFirstName() + " - " + areaName)
                                              .collect(Collectors.toSet())));
    return personWithNameAreaMapping.values().stream()
            .flatMap(mappingString -> mappingString.stream())
            .collect(Collectors.toSet());
  }
}
