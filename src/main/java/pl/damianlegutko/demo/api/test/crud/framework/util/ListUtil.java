package pl.damianlegutko.demo.api.test.crud.framework.util;

import pl.damianlegutko.demo.api.test.crud.domain.books.api.v1.books.Book;

import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class ListUtil {
    public static <Object> java.util.List<Object> filter(java.util.List<Object> listOfObjects, Predicate<Object> filterRule) {
        return listOfObjects.stream()
                .filter(filterRule)
                .collect(toList());
    }

    public static <Object, Rule> java.util.List<Rule> map(java.util.List<Book> listOfObjects, Function<Book, Rule> mapperRule) {
        return listOfObjects.stream()
                .map(mapperRule)
                .collect(toList());
    }
}
