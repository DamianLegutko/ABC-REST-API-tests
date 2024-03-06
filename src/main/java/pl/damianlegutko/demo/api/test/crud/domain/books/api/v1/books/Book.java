package pl.damianlegutko.demo.api.test.crud.domain.books.api.v1.books;

import lombok.Data;
import pl.damianlegutko.demo.api.test.crud.framework.data.Resourceable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import static java.lang.Math.abs;
import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;
import static java.time.Instant.now;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;
import static pl.damianlegutko.demo.api.test.crud.framework.util.ListUtil.filter;
import static pl.damianlegutko.demo.api.test.crud.framework.util.ListUtil.map;

@Data
public class Book implements Resourceable {
    Integer id;
    String name;
    String author;
    String publication;
    String category;
    Integer pages;
    BigDecimal price;

    public static final Integer MINIMUM_PAGES_VALUE = 1;
    public static final Integer MAXIMUM_PAGES_VALUE = 10000;
    public static final BigDecimal MINIMUM_PRICE_VALUE = valueOf(0.01);
    public static final BigDecimal MAXIMUM_PRICE_VALUE = valueOf(10000.00);
    public static final BigDecimal TICK_PRICE = valueOf(0.01);

    public BigDecimal getPrice() {
        return isNull(price)
                ? null
                : price.setScale(2, HALF_UP);
    }

    public static class Operations {
        private static final Random random = new Random(now().toEpochMilli());
        private static final Predicate<Book> hasIncorrectPrice = book -> book.getPrice() == null
                || book.getPrice().compareTo(MINIMUM_PRICE_VALUE) < 0
                || MAXIMUM_PRICE_VALUE.compareTo(book.getPrice()) < 0;

        public static List<Book> findBooksWithIncorrectPrice(List<Book> books) {
            return filter(books, hasIncorrectPrice);
        }

        private static final Predicate<Book> hasIncorrectNumberOfPages = book -> book.getPages() == null
                || book.getPages() < MINIMUM_PAGES_VALUE
                || MAXIMUM_PAGES_VALUE < book.getPages();

        public static List<Book> findBooksWithIncorrectNumberOfPages(List<Book> books) {
            return filter(books, hasIncorrectNumberOfPages);
        }

        public static Predicate<Book> hasIdEqualTo(Integer findId) {
            return book -> book.getId().equals(findId);
        }

        public static List<Book> findBooksWithId(List<Book> books, Integer findId) {
            return filter(books, hasIdEqualTo(findId));
        }

        private static final Predicate<Book> hasIncorrectCategory = book -> !BookCategory.hasValueOf(book.getCategory());

        public static List<Book> findBooksWithIncorrectCategory(List<Book> books) {
            return filter(books, hasIncorrectCategory);
        }

        public static List<Integer> mapToIds(List<Book> books) {
            return map(books, Book::getId);
        }

        public static List<Integer> mapToDistinctIds(List<Book> books) {
            return map(books, Book::getId)
                    .stream().distinct()
                    .toList();
        }

        public static List<Book> findBooksWithDuplicatedIds(List<Book> books) {
            List<Integer> allIds = mapToIds(books);

            List<Integer> distinctIds = mapToDistinctIds(books);

            List<Integer> duplicatedIds = new ArrayList<>(allIds);

            distinctIds.forEach(duplicatedIds::remove);

            return books.stream()
                    .filter(book -> duplicatedIds.contains(book.getId()))
                    .collect(toList());
        }

        public static Integer randomNotExistingIdForBooks(List<Book> books) {
            return randomNotExistingIdForBookIds(mapToIds(books));
        }

        public static Integer randomNotExistingIdForBookIds(List<Integer> ids) {
            Integer generatedId;

            do {
                generatedId = abs(random.nextInt());
            } while (ids.contains(generatedId));

            return generatedId;
        }
    }

    @Override
    public Book deepClone() {
        return deepClone(Book.class);
    }

    @Override
    public String toString() {
        return toJson();
    }
}
