package pl.damianlegutko.demo.api.test.crud.domain.books;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.Test;
import pl.damianlegutko.demo.api.test.crud.domain.books.api.v1.books.Book;
import pl.damianlegutko.demo.api.test.crud.domain.books.common.error.ErrorBody;
import pl.damianlegutko.demo.api.test.crud.framework.tag.data.NonDestructiveTest;
import pl.damianlegutko.demo.api.test.crud.framework.tag.method.GetApiTest;

import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.NORMAL;

@GetApiTest
@Feature("GET by Id endpoint")
public class BookGetByIdTest extends BookEndpointValidationTest {
    private static final String GET_BOOK_STORY = "Get Book by Id";
    private static final String PARAMETER_VALIDATION_STORY = "Get Book by Id parameter validation";

    @Test
    @NonDestructiveTest
    @Story(GET_BOOK_STORY)
    @Severity(BLOCKER)
    @TmsLink("TESTCASE-3")
    void get_existing_book_by_id() {
        Book existingBook = step.ensureExistingBook();

        Book bookById = step.getBookByIdSuccessfully(existingBook.getId());

        step.assertBookEqualsExpected(bookById, existingBook);
    }

    @Test
    @NonDestructiveTest
    @Story(PARAMETER_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-4")
    void cannot_get_not_existing_book_by_id() {
        Integer notExistingBookId = step.getNotExistingId();

        ErrorBody errorBody = step.getBookByIdUnsuccessfully(notExistingBookId);

        ErrorBody expectedErrorBody = errorBodyFactory.bookNotFound(notExistingBookId);

        step.assertBookErrorBodyEqualsExpected(errorBody, expectedErrorBody);
    }
}
