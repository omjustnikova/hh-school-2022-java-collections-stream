package tasks;

import common.Person;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 {

  public static final int FAKED_PERSON_COUNT = 1;
  private long count;

  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  public List<String> getFirstNames(List<Person> persons) {
   if (persons == null) {
      return Collections.emptyList();
   }
   return persons.stream().skip(FAKED_PERSON_COUNT)
            .map(Person::getFirstName)
            .collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется
  public Set<String> getDifferentNames(List<Person> persons) {
    if (persons == null) {
      return Collections.emptySet();
    }
    return getFirstNames(persons).stream().collect(Collectors.toSet());
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  public String getFullName(Person person) {
    String result = "";
    if (person == null) {
      return result;
    }
    return Stream.of(person.getSecondName(), person.getFirstName(), person.getMiddleName())
            .filter(Objects::nonNull)
            .filter(Predicate.not(String::isEmpty))
            .collect(Collectors.joining(" "));
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonFullNames(Collection<Person> persons) {
    if (persons == null) {
      return Collections.emptyMap();
    }
    return persons.stream().collect(Collectors.toMap(Person::getId, this::getFullName));
  }

  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    if (persons1 == null || persons2 == null) {
      return false;
    }
    return persons1.stream()
            .filter(Objects::nonNull)
            .anyMatch(person -> persons2.contains(person));
  }

  //...
  public long countEven(Stream<Integer> numbers) {
    if (numbers == null) {
      return 0;
    }
    return numbers.filter(Objects::nonNull)
                  .filter(num -> num % 2 == 0)
                  .count();
  }

}
