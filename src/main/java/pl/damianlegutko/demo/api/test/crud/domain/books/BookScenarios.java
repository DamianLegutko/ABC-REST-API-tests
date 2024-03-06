package pl.damianlegutko.demo.api.test.crud.domain.books;

import com.google.inject.Inject;
import pl.damianlegutko.demo.api.test.crud.domain.books.api.v1.books.Book;
import pl.damianlegutko.demo.api.test.crud.domain.books.common.error.ErrorBody;
import pl.damianlegutko.demo.api.test.crud.domain.books.common.error.ErrorBodyFactory;
import pl.damianlegutko.demo.api.test.crud.framework.log.Logger;

import static pl.damianlegutko.demo.api.test.crud.domain.books.BookApi.RESOURCE_PATH;

public class BookScenarios {
    final BookSteps step;
    final ErrorBodyFactory errorBodyFactory;
    final Logger logger;

    @Inject
    public BookScenarios(BookSteps steps, ErrorBodyFactory errorBodyFactory, Logger logger) {
        this.step = steps;
        this.errorBodyFactory = errorBodyFactory;
        this.logger = logger;
    }

    public BookScenarios createNewBookTestScenario(Book generatedBook) {
        logger.startScenario();

        Book responseBook = step.createNewBookSuccessfully(generatedBook);

        step.assertBookEqualsExpected(responseBook, generatedBook);

        Book bookById = step.getBookByIdSuccessfully(responseBook.getId());

        step.assertBookEqualsExpected(responseBook, bookById);

        logger.finishScenario();

        return this;
    }

    public BookScenarios failureBookCreationScenario(Book generatedBook, ErrorBody expectedErrorBody) {
        logger.startScenario();

        ErrorBody errorBody = step.createNewBookUnsuccessfully(generatedBook);

        step.assertBookErrorBodyEqualsExpected(errorBody, expectedErrorBody);

        logger.finishScenario();

        return this;
    }

    public BookScenarios failureBookCreationBecauseOfMissingRequiredFieldScenario(Book generatedBook, String missingRequiredField) {
        logger.startScenario();

        ErrorBody expectedErrorBody = errorBodyFactory.missingRequiredField("/" + RESOURCE_PATH, missingRequiredField);

        failureBookCreationScenario(generatedBook, expectedErrorBody);

        logger.finishScenario();

        return this;
    }

    public BookScenarios failureBookUpdateScenario(Book generatedBook, ErrorBody expectedErrorBody) {
        logger.startScenario();

        ErrorBody errorBody = step.updateBookUnsuccessfully(generatedBook);

        step.assertBookErrorBodyEqualsExpected(errorBody, expectedErrorBody);

        return this;
    }

    public BookScenarios failureBookUpdateBecauseOfMissingRequiredFieldScenario(Book bookToUpdate, String missingRequiredField) {
        logger.startScenario();

        ErrorBody expectedErrorBody = errorBodyFactory.missingRequiredField("/" + RESOURCE_PATH + "/" + bookToUpdate.getId(), missingRequiredField);

        failureBookUpdateScenario(bookToUpdate, expectedErrorBody);

        logger.finishScenario();

        return this;
    }

    public BookScenarios updateBookScenario(Book newVersionOfBook) {
        logger.startScenario();

        Book updateBookResponse = step.updateBookSuccessfully(newVersionOfBook);

        step.assertBookEqualsExpected(updateBookResponse, newVersionOfBook);

        Book bookById = step.getBookByIdSuccessfully(newVersionOfBook.getId());

        step.assertBookEqualsExpected(bookById, newVersionOfBook);

        logger.finishScenario();

        return this;
    }

    public BookScenarios updateNotExistingBookScenario(Book newVersionOfBook, Integer bookIdToUpdate) {
        logger.startScenario();

        ErrorBody errorBody = step.updateBookUnsuccessfully(newVersionOfBook, bookIdToUpdate);

        ErrorBody expectedErrorBody = errorBodyFactory.bookNotFound(bookIdToUpdate);

        step.assertBookErrorBodyEqualsExpected(errorBody, expectedErrorBody);

        logger.finishScenario();

        return this;
    }
}
