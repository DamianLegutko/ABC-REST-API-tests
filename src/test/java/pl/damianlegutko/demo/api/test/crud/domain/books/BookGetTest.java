package pl.damianlegutko.demo.api.test.crud.domain.books;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.damianlegutko.demo.api.test.crud.domain.books.api.v1.books.Book;
import pl.damianlegutko.demo.api.test.crud.framework.tag.data.NonDestructiveTest;
import pl.damianlegutko.demo.api.test.crud.framework.tag.method.GetApiTest;

import java.util.List;

import static io.qameta.allure.SeverityLevel.*;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.damianlegutko.demo.api.test.crud.domain.books.api.v1.books.Book.Operations.*;

@GetApiTest
@Feature("GET endpoint")
public class BookGetTest extends BookEndpointValidationTest {
    private static final String GET_BOOK_STORY = "Get Book";
    private static final String RESPONSE_VALIDATION_STORY = "Get Book response validation";

    @BeforeEach
    void beforeEach() {
        step.ensureNewRandomBook();
    }

    @Test
    @NonDestructiveTest
    @Story(GET_BOOK_STORY)
    @Severity(BLOCKER)
    @TmsLink("TESTCASE-5")
    void get_all_books() {
        List<Book> books = step.getAllBooksForDefaultUser();

        assertThat(books)
                .isNotNull()
                .hasSizeGreaterThan(0);
    }

    @Test
    @NonDestructiveTest
    @Story(RESPONSE_VALIDATION_STORY)
    @Severity(MINOR)
    @TmsLink("TESTCASE-6")
    void books_have_positive_number_of_pages() {
        List<Book> books = step.getAllBooksForDefaultUser();

        assertThat(findBooksWithIncorrectNumberOfPages(books))
                .isNotNull()
                .as("Books with incorrect 'pages' returned by GET books endpoint")
                .hasSize(0);
    }

    @Test
    @NonDestructiveTest
    @Story(RESPONSE_VALIDATION_STORY)
    @Severity(MINOR)
    @TmsLink("TESTCASE-7")
    void books_have_positive_price() {
        List<Book> books = step.getAllBooksForDefaultUser();

        assertThat(findBooksWithIncorrectPrice(books))
                .isNotNull()
                .as("Books with incorrect 'price' returned by GET books endpoint")
                .hasSize(0);
    }

    @Test
    @NonDestructiveTest
    @Story(RESPONSE_VALIDATION_STORY)
    @Severity(MINOR)
    @TmsLink("TESTCASE-8")
    void books_have_validate_categories() {
        List<Book> books = step.getAllBooksForDefaultUser();

        assertThat(findBooksWithIncorrectCategory(books))
                .isNotNull()
                .as("Books with incorrect 'category' returned by GET books endpoint")
                .hasSize(0);
    }

    @Test
    @NonDestructiveTest
    @Story(RESPONSE_VALIDATION_STORY)
    @Severity(MINOR)
    @TmsLink("TESTCASE-9")
    void books_have_unique_id() {
        List<Book> books = step.getAllBooksForDefaultUser();

        List<Book> duplicatedBooks = findBooksWithDuplicatedIds(books);

        assertThat(duplicatedBooks)
                .isNotNull()
                .as("Books with duplicated 'Id's returned by GET books endpoint")
                .hasSize(0);
    }
}
