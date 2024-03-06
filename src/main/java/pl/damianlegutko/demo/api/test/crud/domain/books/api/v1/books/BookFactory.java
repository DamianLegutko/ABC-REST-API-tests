package pl.damianlegutko.demo.api.test.crud.domain.books.api.v1.books;

import com.google.inject.Inject;
import pl.damianlegutko.demo.api.test.crud.framework.DataGenerator;
import pl.damianlegutko.demo.api.test.crud.framework.log.Logger;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;
import static pl.damianlegutko.demo.api.test.crud.framework.util.RandomUtil.RANDOM;

public class BookFactory {
    DataGenerator data;
    Logger logger;

    @Inject
    public BookFactory(DataGenerator data,Logger logger) {
        this.data = data;
        this.logger = logger;
    }

    public Book generateRandomBook() {
        BigDecimal price = valueOf(RANDOM.nextDouble())
                .multiply(valueOf(333))
                .setScale(2, HALF_UP);
        com.github.javafaker.Book book = data.getGenerate().book();

        Book generatedBook = new Book()
                .setName(book.title())
                .setAuthor(book.author())
                .setCategory(book.genre())
                .setPublication(book.publisher())
                .setPages(RANDOM.nextInt(1000) + 1)
                .setPrice(price);

        logger.stepAction("Generated random Book", generatedBook);

        return generatedBook;
    }
}
