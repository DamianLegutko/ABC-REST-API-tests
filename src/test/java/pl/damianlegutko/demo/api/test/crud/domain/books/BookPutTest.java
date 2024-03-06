package pl.damianlegutko.demo.api.test.crud.domain.books;

import com.google.inject.Inject;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.Test;
import pl.damianlegutko.demo.api.test.crud.domain.books.api.v1.books.Book;
import pl.damianlegutko.demo.api.test.crud.domain.books.common.error.ErrorBody;
import pl.damianlegutko.demo.api.test.crud.framework.DataGenerator;
import pl.damianlegutko.demo.api.test.crud.framework.tag.data.DestructiveTest;
import pl.damianlegutko.demo.api.test.crud.framework.tag.method.PutApiTest;

import static io.qameta.allure.SeverityLevel.*;
import static java.lang.String.format;
import static pl.damianlegutko.demo.api.test.crud.domain.books.BookApi.RESOURCE_PATH;
import static pl.damianlegutko.demo.api.test.crud.domain.books.api.v1.books.Book.*;
import static pl.damianlegutko.demo.api.test.crud.framework.util.RandomUtil.RANDOM;
import static pl.damianlegutko.demo.api.test.crud.framework.util.RandomUtil.positiveRandomBigDecimal;
import static pl.damianlegutko.demo.api.test.crud.framework.util.StringUtil.MOST_COMMON_SPECIAL_CHARS;
import static pl.damianlegutko.demo.api.test.crud.framework.util.StringUtil.POLISH_CHARS;

@PutApiTest
@DestructiveTest
@Feature("PUT endpoint")
public class BookPutTest extends BookEndpointValidationTest {
    private static final String UPDATE_BOOK_STORY = "Update Book";
    private static final String PAYLOAD_VALIDATION_STORY = "Update Book payload validation";
    private static final String PARAMETER_VALIDATION_STORY = "Update Book parameter validation";

    @Inject
    DataGenerator dataGenerator;

    @Test
    @Story(UPDATE_BOOK_STORY)
    @Severity(CRITICAL)
    @TmsLink("TESTCASE-29")
    void update_new_default_book_without_any_changes() {
        scenario.updateBookScenario(step.ensureNewRandomBook());
    }

    @Test
    @Story(UPDATE_BOOK_STORY)
    @Severity(CRITICAL)
    @TmsLink("TESTCASE-30")
    void update_book_with_a_new_price() {
        Book newVersionOfBook = step.ensureNewRandomBook()
                .setPrice(positiveRandomBigDecimal());
        scenario.updateBookScenario(newVersionOfBook);
    }

    @Test
    @Story(UPDATE_BOOK_STORY)
    @Severity(CRITICAL)
    @TmsLink("TESTCASE-31")
    void update_book_with_a_new_publisher() {
        com.github.javafaker.Book book = dataGenerator.getGenerate().book();

        Book newVersionOfBook = step.ensureNewRandomBook()
                .setPublication(book.publisher());
        scenario.updateBookScenario(newVersionOfBook);
    }

    @Test
    @Story(UPDATE_BOOK_STORY)
    @Severity(CRITICAL)
    @TmsLink("TESTCASE-32")
    void update_book_with_a_new_author() {
        com.github.javafaker.Book book = dataGenerator.getGenerate().book();

        Book newVersionOfBook = step.ensureNewRandomBook()
                .setAuthor(book.author());
        scenario.updateBookScenario(newVersionOfBook);
    }

    @Test
    @Story(UPDATE_BOOK_STORY)
    @Severity(CRITICAL)
    @TmsLink("TESTCASE-33")
    void update_book_with_a_new_name() {
        com.github.javafaker.Book book = dataGenerator.getGenerate().book();

        Book newVersionOfBook = step.ensureNewRandomBook()
                .setName(book.title());
        scenario.updateBookScenario(newVersionOfBook);
    }

    @Test
    @Story(UPDATE_BOOK_STORY)
    @Severity(CRITICAL)
    @TmsLink("TESTCASE-34")
    void update_book_with_a_new_amount_of_pages() {
        Book newVersionOfBook = step.ensureNewRandomBook()
                .setPages(RANDOM.nextInt(MAXIMUM_PAGES_VALUE));

        scenario.updateBookScenario(newVersionOfBook);
    }

    @Test
    @Story(UPDATE_BOOK_STORY)
    @Severity(BLOCKER)
    @TmsLink("TESTCASE-35")
    void update_book_with_all_new_changeable_values() {
        com.github.javafaker.Book book = dataGenerator.getGenerate().book();

        Book newVersionOfBook = step.ensureNewRandomBook()
                .setPrice(positiveRandomBigDecimal())
                .setPublication(book.publisher())
                .setAuthor(book.author())
                .setName(book.title())
                .setPages(RANDOM.nextInt(MAXIMUM_PAGES_VALUE));

        scenario.updateBookScenario(newVersionOfBook);
    }

    @Test
    @Story(PARAMETER_VALIDATION_STORY)
    @Severity(MINOR)
    @TmsLink("TESTCASE-36")
    void update_not_existing_book() {
        scenario.updateNotExistingBookScenario(step.ensureNewRandomBook(), step.getNotExistingId());
    }

    @Test
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(MINOR)
    @TmsLink("TESTCASE-37")
    void update_existing_book_with_not_existing_id() {
        Book newBook = step.ensureNewRandomBook();

        Book bookToUpdate = newBook.deepClone()
                .setId(step.getNotExistingId());

        ErrorBody expectedErrorBody = new ErrorBody()
                .setPath("/" + RESOURCE_PATH + "/" + newBook.getId())
                .setStatus(400)
                .setError("The provided ID in the body does not match the ID in the path. You cannot change the ID of an existing object.");

        ErrorBody errorBody = step.updateBookUnsuccessfully(bookToUpdate, newBook.getId());

        step.assertBookErrorBodyEqualsExpected(errorBody, expectedErrorBody);
    }

    @Test
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(MINOR)
    @TmsLink("TESTCASE-38")
    void update_book_with_text_fields_contain_polish_chars() {
        Book bookToUpdate = step.ensureNewRandomBook()
                .setName(POLISH_CHARS)
                .setAuthor(POLISH_CHARS)
                .setPublication(POLISH_CHARS);

        scenario.updateBookScenario(bookToUpdate);
    }

    @Test
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(MINOR)
    @TmsLink("TESTCASE-39")
    void update_book_with_text_fields_contain_most_common_special_chars() {
        Book bookToUpdate = step.ensureNewRandomBook()
                .setName(MOST_COMMON_SPECIAL_CHARS)
                .setAuthor(MOST_COMMON_SPECIAL_CHARS)
                .setPublication(MOST_COMMON_SPECIAL_CHARS);

        scenario.updateBookScenario(bookToUpdate);
    }

    @Test
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-40")
    void update_book_with_null_name() {
        Book generatedBook = step.ensureNewRandomBook()
                .setName(null);

        scenario.failureBookUpdateBecauseOfMissingRequiredFieldScenario(generatedBook, "name");
    }

    @Test
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-41")
    void update_book_with_null_author() {
        Book generatedBook = step.ensureNewRandomBook()
                .setAuthor(null);

        scenario.failureBookUpdateBecauseOfMissingRequiredFieldScenario(generatedBook, "author");
    }

    @Test
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-42")
    void update_book_with_null_publication() {
        Book generatedBook = step.ensureNewRandomBook()
                .setPublication(null);

        scenario.failureBookUpdateBecauseOfMissingRequiredFieldScenario(generatedBook, "publication");
    }

    @Test
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-43")
    void update_book_with_null_price() {
        Book generatedBook = step.ensureNewRandomBook()
                .setPrice(null);

        scenario.failureBookUpdateBecauseOfMissingRequiredFieldScenario(generatedBook, "price");
    }

    @Test
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-44")
    void update_book_with_null_category() {
        Book generatedBook = step.ensureNewRandomBook()
                .setCategory(null);

        scenario.failureBookUpdateBecauseOfMissingRequiredFieldScenario(generatedBook, "category");
    }

    @Test
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-45")
    void update_book_with_incorrect_category() {
        Book generatedBook = step.ensureNewRandomBook()
                .setCategory("Incorrect book category");

        ErrorBody expectedErrorBody = new ErrorBody()
                .setError("Provided category value is not valid. Please refer to the documentation for a list of acceptable category values.")
                .setPath("/api/v1/books")
                .setStatus(400);

        scenario.failureBookUpdateScenario(generatedBook, expectedErrorBody);
    }

    @Test
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-46")
    void update_book_with_number_of_pages_below_minimum() {
        Book generatedBook = step.ensureNewRandomBook()
                .setPages(MINIMUM_PAGES_VALUE - 1);

        ErrorBody expectedErrorBody = new ErrorBody()
                .setError(format("The number of pages in a book must be between %s and %s. Please provide a valid page count for the book.", MINIMUM_PAGES_VALUE, MAXIMUM_PAGES_VALUE))
                .setPath("/api/v1/books")
                .setStatus(400);

        scenario.failureBookUpdateScenario(generatedBook, expectedErrorBody);
    }

    @Test
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-47")
    void update_book_with_minimum_number_of_pages() {
        Book generatedBook = step.ensureNewRandomBook()
                .setPages(MINIMUM_PAGES_VALUE);

        scenario.updateBookScenario(generatedBook);
    }

    @Test
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-48")
    void update_book_with_maximum_number_of_pages() {
        Book generatedBook = step.ensureNewRandomBook()
                .setPages(MAXIMUM_PAGES_VALUE);

        scenario.updateBookScenario(generatedBook);
    }

    @Test
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-49")
    void update_book_with_number_of_pages_above_maximum() {
        Book generatedBook = step.ensureNewRandomBook()
                .setPages(MAXIMUM_PAGES_VALUE + 1);

        ErrorBody expectedErrorBody = new ErrorBody()
                .setError(format("The number of pages in a book must be between %s and %s. Please provide a valid page count for the book.", MINIMUM_PAGES_VALUE, MAXIMUM_PAGES_VALUE))
                .setPath("/api/v1/books")
                .setStatus(400);

        scenario.failureBookUpdateScenario(generatedBook, expectedErrorBody);
    }

    @Test
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-50")
    void update_book_with_price_below_minimum() {
        Book generatedBook = step.ensureNewRandomBook()
                .setPrice(MINIMUM_PRICE_VALUE.subtract(TICK_PRICE));

        ErrorBody expectedErrorBody = new ErrorBody()
                .setError(format("The book price must be between %s and %s. Please provide a valid price for the book.", MINIMUM_PRICE_VALUE, MAXIMUM_PRICE_VALUE))
                .setPath("/api/v1/books")
                .setStatus(400);

        scenario.failureBookUpdateScenario(generatedBook, expectedErrorBody);
    }

    @Test
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-51")
    void update_book_with_minimum_prices() {
        Book generatedBook = step.ensureNewRandomBook()
                .setPrice(MINIMUM_PRICE_VALUE);

        scenario.updateBookScenario(generatedBook);
    }

    @Test
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-52")
    void update_book_with_maximum_price() {
        Book generatedBook = step.ensureNewRandomBook()
                .setPrice(MAXIMUM_PRICE_VALUE);

        scenario.updateBookScenario(generatedBook);
    }

    @Test
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-53")
    void update_book_with_price_above_maximum() {
        Book generatedBook = step.ensureNewRandomBook()
                .setPrice(MAXIMUM_PRICE_VALUE.add(TICK_PRICE));

        ErrorBody expectedErrorBody = new ErrorBody()
                .setError(format("The book price must be between %s and %s. Please provide a valid price for the book.", MINIMUM_PRICE_VALUE, MAXIMUM_PRICE_VALUE))
                .setPath("/api/v1/books")
                .setStatus(400);

        scenario.failureBookUpdateScenario(generatedBook, expectedErrorBody);
    }
}
