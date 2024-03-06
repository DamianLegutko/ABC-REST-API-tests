package pl.damianlegutko.demo.api.test.crud.domain.books;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.Test;
import pl.damianlegutko.demo.api.test.crud.domain.books.api.v1.books.Book;
import pl.damianlegutko.demo.api.test.crud.domain.books.common.error.ErrorBody;
import pl.damianlegutko.demo.api.test.crud.framework.tag.data.NonDestructiveTest;
import pl.damianlegutko.demo.api.test.crud.framework.tag.method.PostApiTest;

import static io.qameta.allure.SeverityLevel.*;
import static java.lang.String.format;
import static pl.damianlegutko.demo.api.test.crud.domain.books.api.v1.books.Book.*;
import static pl.damianlegutko.demo.api.test.crud.framework.util.StringUtil.MOST_COMMON_SPECIAL_CHARS;
import static pl.damianlegutko.demo.api.test.crud.framework.util.StringUtil.POLISH_CHARS;

@PostApiTest
@Feature("POST endpoint")
public class BookPostTest extends BookEndpointValidationTest {
    private static final String CREATE_BOOK_STORY = "Create Book";
    private static final String PAYLOAD_VALIDATION_STORY = "Create Book payload validation";

    @Test
    @NonDestructiveTest
    @Story(CREATE_BOOK_STORY)
    @Severity(BLOCKER)
    @TmsLink("TESTCASE-10")
    void create_new_default_book() {
        scenario.createNewBookTestScenario(step.generateRandomBookPayload());
    }

    @Test
    @NonDestructiveTest
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(MINOR)
    @TmsLink("TESTCASE-11")
    void create_new_book_with_text_fields_contain_polish_chars() {
        Book generatedBook = step.generateRandomBookPayload()
                .setName(POLISH_CHARS)
                .setAuthor(POLISH_CHARS)
                .setPublication(POLISH_CHARS);

        scenario.createNewBookTestScenario(generatedBook);
    }

    @Test
    @NonDestructiveTest
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(MINOR)
    @TmsLink("TESTCASE-12")
    void create_new_book_with_text_fields_contain_most_common_special_chars() {
        Book generatedBook = step.generateRandomBookPayload()
                .setName(MOST_COMMON_SPECIAL_CHARS)
                .setAuthor(MOST_COMMON_SPECIAL_CHARS)
                .setPublication(MOST_COMMON_SPECIAL_CHARS);

        scenario.createNewBookTestScenario(generatedBook);
    }

    @Test
    @NonDestructiveTest
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(MINOR)
    @TmsLink("TESTCASE-13")
    void create_new_book_with_set_existing_id() {
        Integer notExistingBookId = step.getExistingId();

        Book generatedBook = step.generateRandomBookPayload()
                .setId(notExistingBookId);

        scenario.createNewBookTestScenario(generatedBook);
    }

    @Test
    @NonDestructiveTest
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(MINOR)
    @TmsLink("TESTCASE-14")
    void create_new_book_with_set_not_existing_id() {
        Integer notExistingBookId = step.getNotExistingId();

        Book generatedBook = step.generateRandomBookPayload()
                .setId(notExistingBookId);

        scenario.createNewBookTestScenario(generatedBook);
    }

    @Test
    @NonDestructiveTest
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-15")
    void create_new_book_with_null_name() {
        Book generatedBook = step.generateRandomBookPayload()
                .setName(null);

        scenario.failureBookCreationBecauseOfMissingRequiredFieldScenario(generatedBook, "name");
    }

    @Test
    @NonDestructiveTest
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-16")
    void create_new_book_with_null_author() {
        Book generatedBook = step.generateRandomBookPayload()
                .setAuthor(null);

        scenario.failureBookCreationBecauseOfMissingRequiredFieldScenario(generatedBook, "author");
    }

    @Test
    @NonDestructiveTest
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-17")
    void create_new_book_with_null_publication() {
        Book generatedBook = step.generateRandomBookPayload()
                .setPublication(null);

        scenario.failureBookCreationBecauseOfMissingRequiredFieldScenario(generatedBook, "publication");
    }

    @Test
    @NonDestructiveTest
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-18")
    void create_new_book_with_null_price() {
        Book generatedBook = step.generateRandomBookPayload()
                .setPrice(null);

        scenario.failureBookCreationBecauseOfMissingRequiredFieldScenario(generatedBook, "price");
    }

    @Test
    @NonDestructiveTest
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-19")
    void create_new_book_with_null_category() {
        Book generatedBook = step.generateRandomBookPayload()
                .setCategory(null);

        scenario.failureBookCreationBecauseOfMissingRequiredFieldScenario(generatedBook, "category");
    }

    @Test
    @NonDestructiveTest
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-20")
    void create_new_book_with_incorrect_category() {
        Book generatedBook = step.generateRandomBookPayload()
                .setCategory("Incorrect book category");

        ErrorBody expectedErrorBody = new ErrorBody()
                .setError("Provided category value is not valid. Please refer to the documentation for a list of acceptable category values.")
                .setPath("/api/v1/books")
                .setStatus(400);

        scenario.failureBookCreationScenario(generatedBook, expectedErrorBody);
    }

    @Test
    @NonDestructiveTest
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-21")
    void create_new_book_with_number_of_pages_below_minimum() {
        Book generatedBook = step.generateRandomBookPayload()
                .setPages(MINIMUM_PAGES_VALUE - 1);

        ErrorBody expectedErrorBody = new ErrorBody()
                .setError(format("The number of pages in a book must be between %s and %s. Please provide a valid page count for the book.", MINIMUM_PAGES_VALUE, MAXIMUM_PAGES_VALUE))
                .setPath("/api/v1/books")
                .setStatus(400);

        scenario.failureBookCreationScenario(generatedBook, expectedErrorBody);
    }

    @Test
    @NonDestructiveTest
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-22")
    void create_new_book_with_minimum_number_of_pages() {
        Book generatedBook = step.generateRandomBookPayload()
                .setPages(MINIMUM_PAGES_VALUE);

        scenario.createNewBookTestScenario(generatedBook);
    }

    @Test
    @NonDestructiveTest
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-23")
    void create_new_book_with_maximum_number_of_pages() {
        Book generatedBook = step.generateRandomBookPayload()
                .setPages(MAXIMUM_PAGES_VALUE);

        scenario.createNewBookTestScenario(generatedBook);
    }

    @Test
    @NonDestructiveTest
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-24")
    void create_new_book_with_number_of_pages_above_maximum() {
        Book generatedBook = step.generateRandomBookPayload()
                .setPages(MAXIMUM_PAGES_VALUE + 1);

        ErrorBody expectedErrorBody = new ErrorBody()
                .setError(format("The number of pages in a book must be between %s and %s. Please provide a valid page count for the book.", MINIMUM_PAGES_VALUE, MAXIMUM_PAGES_VALUE))
                .setPath("/api/v1/books")
                .setStatus(400);

        scenario.failureBookCreationScenario(generatedBook, expectedErrorBody);
    }

    @Test
    @NonDestructiveTest
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-25")
    void create_new_book_with_price_below_minimum() {
        Book generatedBook = step.generateRandomBookPayload()
                .setPrice(MINIMUM_PRICE_VALUE.subtract(TICK_PRICE));

        ErrorBody expectedErrorBody = new ErrorBody()
                .setError(format("The book price must be between %s and %s. Please provide a valid price for the book.", MINIMUM_PRICE_VALUE, MAXIMUM_PRICE_VALUE))
                .setPath("/api/v1/books")
                .setStatus(400);

        scenario.failureBookCreationScenario(generatedBook, expectedErrorBody);
    }

    @Test
    @NonDestructiveTest
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-26")
    void create_new_book_with_minimum_prices() {
        Book generatedBook = step.generateRandomBookPayload()
                .setPrice(MINIMUM_PRICE_VALUE);

        scenario.createNewBookTestScenario(generatedBook);
    }

    @Test
    @NonDestructiveTest
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-27")
    void create_new_book_with_maximum_price() {
        Book generatedBook = step.generateRandomBookPayload()
                .setPrice(MAXIMUM_PRICE_VALUE);

        scenario.createNewBookTestScenario(generatedBook);
    }

    @Test
    @NonDestructiveTest
    @Story(PAYLOAD_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-28")
    void create_new_book_with_price_above_maximum() {
        Book generatedBook = step.generateRandomBookPayload()
                .setPrice(MAXIMUM_PRICE_VALUE.add(TICK_PRICE));

        ErrorBody expectedErrorBody = new ErrorBody()
                .setError(format("The book price must be between %s and %s. Please provide a valid price for the book.", MINIMUM_PRICE_VALUE, MAXIMUM_PRICE_VALUE))
                .setPath("/api/v1/books")
                .setStatus(400);

        scenario.failureBookCreationScenario(generatedBook, expectedErrorBody);
    }
}
