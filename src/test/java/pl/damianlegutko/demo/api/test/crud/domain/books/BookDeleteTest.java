package pl.damianlegutko.demo.api.test.crud.domain.books;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.Test;
import pl.damianlegutko.demo.api.test.crud.domain.books.api.v1.books.Book;
import pl.damianlegutko.demo.api.test.crud.domain.books.common.error.ErrorBody;
import pl.damianlegutko.demo.api.test.crud.framework.tag.data.DestructiveTest;
import pl.damianlegutko.demo.api.test.crud.framework.tag.data.NonDestructiveTest;
import pl.damianlegutko.demo.api.test.crud.framework.tag.method.DeleteApiTest;

import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.assertj.core.api.Assertions.assertThat;

@DeleteApiTest
@Feature("DELETE endpoint")
public class BookDeleteTest extends BookEndpointValidationTest {
    private static final String DELETE_BOOK_STORY = "Delete Book";
    private static final String PARAMETER_VALIDATION_STORY = "Delete Book parameter validation";
    @Test
    @DestructiveTest
    @Story(DELETE_BOOK_STORY)
    @Severity(BLOCKER)
    @TmsLink("TESTCASE-1")
    void delete_existing_book() {
        Book bookToBeDeleted = step.ensureExistingBook();

        Integer bookIdToDelete = bookToBeDeleted.getId();

        String planTextResponse = step.deleteBookSuccessfully(bookIdToDelete);

        assertThat(planTextResponse)
                .isEqualTo("Book has been deleted successfully");

        ErrorBody errorBody = step.getBookByIdUnsuccessfully(bookIdToDelete);

        ErrorBody expectedErrorBody = errorBodyFactory.bookNotFound(bookIdToDelete);

        step.assertBookErrorBodyEqualsExpected(errorBody, expectedErrorBody);
    }

    @Test
    @NonDestructiveTest
    @Story(PARAMETER_VALIDATION_STORY)
    @Severity(NORMAL)
    @TmsLink("TESTCASE-2")
    void cannot_delete_not_existing_book() {
        Integer notExistingBookId = step.getNotExistingId();

        ErrorBody errorBody = step.getBookByIdUnsuccessfully(notExistingBookId);

        ErrorBody expectedErrorBody = errorBodyFactory.bookNotFound(notExistingBookId);

        step.assertBookErrorBodyEqualsExpected(errorBody, expectedErrorBody);
    }
}
