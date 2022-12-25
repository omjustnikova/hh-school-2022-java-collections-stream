package tasks;

import common.Company;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/*
Из коллекции компаний необходимо получить всевозможные различные названия вакансий
 */
public class Task7 {

  public static Set<String> vacancyNames(Collection<Company> companies) {
    return companies.stream()
            .map(company -> company.getVacancies())
            .flatMap(vacancies -> vacancies.stream())
            .map(vacancy -> vacancy.getTitle())
            .collect(Collectors.toSet());
  }

}
