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


    Map<Integer, String> areaMap = areas.stream()
                                        .collect(Collectors.toMap(area -> area.getId(),
                                                                  area -> area.getName()));
    Map<Person, Set<String>> personWithNameAreaMapping =
            persons.stream().collect(
                    Collectors.toMap(person -> person,
                                     person -> personAreaIds.get(person.getId()).stream()
                                               .map(areaId -> person.getFirstName() + " - " + areaMap.get(areaId))
                                               .collect(Collectors.toSet())));

    return personWithNameAreaMapping.values().stream()
            .flatMap(mappingString -> mappingString.stream())
            .collect(Collectors.toSet());
  }
}
