package pl.damianlegutko.demo.api.test.crud.domain.books;

import com.google.inject.Inject;
import io.qameta.allure.Step;
import pl.damianlegutko.demo.api.test.crud.domain.books.api.v1.books.Book;
import pl.damianlegutko.demo.api.test.crud.domain.books.api.v1.books.BookFactory;
import pl.damianlegutko.demo.api.test.crud.domain.books.common.error.ErrorBody;
import pl.damianlegutko.demo.api.test.crud.framework.auth.Tokenable;
import pl.damianlegutko.demo.api.test.crud.framework.log.Logger;

import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.damianlegutko.demo.api.test.crud.domain.books.api.v1.books.Book.Operations.randomNotExistingIdForBooks;
import static pl.damianlegutko.demo.api.test.crud.domain.books.api.v1.books.BookAssert.assertBook;
import static pl.damianlegutko.demo.api.test.crud.domain.books.common.error.ErrorBodyAssert.assertErrorBody;

public class BookSteps {
    final BookService service;
    final BookFactory factory;
    final Logger logger;

    @Inject
    public BookSteps(BookService service, BookFactory factory, Logger logger) {
        this.service = service;
        this.factory = factory;
        this.logger = logger;
    }

    @Step("Generate random Book payload")
    Book generateRandomBookPayload() {
        logger.startStep();

        Book book = factory.generateRandomBook();

        logger.finishStep();

        return book;
    }

    @Step("Ensure new random Book")
    public Book ensureNewRandomBook() {
        logger.startStep();

        Book generatedBook = factory.generateRandomBook();

        Book bookResponse = createNewBookSuccessfully(generatedBook);

        assertBookEqualsExpected(bookResponse, generatedBook);

        Book book = bookResponse.deepClone();

        logger.stepAction(format("The existence of a book with ID %s was ensured", book.getId()), book)
                .finishStep();

        return book;
    }

    @Step("GET all Books for default user")
    List<Book> getAllBooksForDefaultUser() {
        logger.startStep();

        List<Book> responseBodyOnSuccess = service.getBooksRequest()
                .getResponseBodyOnSuccess();

        logger.stepAction(format("%s amount of Books were GET", responseBodyOnSuccess.size()), responseBodyOnSuccess.size())
                .finishStep();

        return responseBodyOnSuccess;
    }

    @Step("GET all Books")
    List<Book> getAllBooks(Tokenable apiUser) {
        logger.startStep();

        List<Book> responseBodyOnSuccess = service.getBooksRequest(apiUser.getAuthorizationToken())
                .getResponseBodyOnSuccess();

        logger.stepAction(format("%s amount of Books were GET", responseBodyOnSuccess.size()), responseBodyOnSuccess.size())
                .finishStep();

        return responseBodyOnSuccess;
    }

    @Step("GET Book ids for default user")
    List<Integer> getBookIdsForDefaultUser() {
        logger.startStep();

        List<Integer> ids = getAllBooksForDefaultUser()
                .stream()
                .map(Book::getId)
                .collect(toList());

        logger.stepAction(format("%s amount of Book Ids were GET", ids.size()), ids)
                .finishStep();

        return ids;
    }

    @Step("GET all Books ids")
    List<Integer> getAllBooksIds(Tokenable apiUser) {
        logger.startStep();

        List<Integer> ids = getAllBooks(apiUser)
                .stream()
                .map(Book::getId)
                .collect(toList());

        logger.stepAction(format("%s amount of Book Ids were GET", ids.size()), ids)
                .finishStep();

        return ids;
    }

    @Step("Ensure existing Book")
    public Book ensureExistingBook() {
        logger.startStep();

        List<Book> books = service.getBooksRequest()
                .getResponseBodyOnSuccess();

        Book result = books.isEmpty()
                ? ensureNewRandomBook()
                : books.get(0);

        logger.stepAction(format("The existence of a book with ID %s was ensured", result.getId()), result)
                .finishStep();

        return result;
    }

    @Step("POST new Book successfully")
    public Book createNewBookSuccessfully(Book bookToCreate) {
        logger.startStep();

        Book book = service.createBookRequest(bookToCreate)
                .getResponseBodyOnSuccess()
                .deepClone();

        logger.stepAction(format("POSTed Book with id %s", book.getId()), book)
                .finishStep();

        return book;
    }

    @Step("POST new Book unsuccessfully")
    public ErrorBody createNewBookUnsuccessfully(Book bookToCreate) {
        logger.startStep();

        ErrorBody errorBodyOnFailure = service.createBookRequest(bookToCreate)
                .getErrorBodyOnFailure(ErrorBody.class);

        logger.stepAction(format("Responded ErrorBody with response code %s", errorBodyOnFailure.getStatus()), errorBodyOnFailure)
                .finishStep();

        return errorBodyOnFailure;
    }

    @Step("Get not existing id")
    public Integer getNotExistingId() {
        logger.startStep();

        Integer notExistingId = randomNotExistingIdForBooks(service.getBooksRequest()
                .getResponseBodyOnSuccess());

        logger.stepAction(format("Generated not existing Book Id %s", notExistingId), notExistingId)
                .finishStep();

        return notExistingId;
    }

    @Step("Get existing id")
    public Integer getExistingId() {
        logger.startStep();

        List<Book> responseBodyOnSuccess = service.getBooksRequest()
                .getResponseBodyOnSuccess();

        assertThat(responseBodyOnSuccess)
                .hasSizeGreaterThan(0);

        Integer id = responseBodyOnSuccess.get(0)
                .getId();

        logger.stepAction(format("Existing Book Id found %s", id), id)
                .finishStep();

        return id;
    }

    @Step("UPDATE Book successfully")
    public Book updateBookSuccessfully(Book book, Integer bookIdToUpdate) {
        logger.startStep();

        Book bookResponse = service.updateBookRequest(book, bookIdToUpdate)
                .getResponseBodyOnSuccess();

        logger.stepAction(format("PUTed Book with id %s", bookResponse.getId()), book)
                .finishStep();

        return bookResponse;
    }

    @Step("UPDATE Book successfully")
    public Book updateBookSuccessfully(Book book) {
        return updateBookSuccessfully(book, book.getId());
    }

    @Step("UPDATE Book unsuccessfully")
    public ErrorBody updateBookUnsuccessfully(Book book, Integer bookIdToUpdate) {
        logger.startStep();

        ErrorBody errorBodyOnFailure = service.updateBookRequest(book, bookIdToUpdate)
                .getErrorBodyOnFailure(ErrorBody.class);

        logger.stepAction(format("Responded ErrorBody with response code %s", errorBodyOnFailure.getStatus()), errorBodyOnFailure)
                .finishStep();

        return errorBodyOnFailure;
    }

    @Step("UPDATE Book unsuccessfully")
    public ErrorBody updateBookUnsuccessfully(Book book) {
        logger.startStep();

        ErrorBody errorBodyOnFailure = updateBookUnsuccessfully(book, book.getId());

        logger.stepAction(format("Responded ErrorBody with response code %s", errorBodyOnFailure.getStatus()), errorBodyOnFailure)
                .finishStep();

        return errorBodyOnFailure;
    }

    @Step("GET Book by Id successfully")
    public Book getBookByIdSuccessfully(Integer bookId) {
        logger.startStep();

        Book responseBodyOnSuccess = service.getBookByIdRequest(bookId)
                .getResponseBodyOnSuccess();

        logger.stepAction(format("Was GET Book by id %s", bookId), responseBodyOnSuccess)
                .finishStep();

        return responseBodyOnSuccess;
    }

    @Step("GET Book by Id unsuccessfully")
    public ErrorBody getBookByIdUnsuccessfully(Integer bookId) {
        logger.startStep();

        ErrorBody errorBodyOnFailure = service.getBookByIdRequest(bookId)
                .getErrorBodyOnFailure(ErrorBody.class);

        logger.stepAction(format("Responded ErrorBody with response code %s", errorBodyOnFailure.getStatus()), errorBodyOnFailure)
                .finishStep();

        return errorBodyOnFailure;
    }

    @Step("DELETE Book successfully")
    public String deleteBookSuccessfully(Integer bookIdToDelete) {
        logger.startStep();

        String planTextStringOnSuccess = service.deleteBookRequest(bookIdToDelete)
                .getPlanTextStringOnSuccess();

        logger.stepAction(format("DELETEd Book by id %s", bookIdToDelete), planTextStringOnSuccess)
                .finishStep();

        return planTextStringOnSuccess;
    }

    @Step("Assert Book Error Body equals expected")
    public BookSteps assertBookEqualsExpected(Book actual, Book expected) {
        logger.startStep();

        assertBook(actual)
                .addIgnoreField("id")
                .isRecursiveEqualTo(expected);

        logger.stepAction("Asserted Book", actual)
                .finishStep();

        return this;
    }

    @Step("Assert Book Error Body equals expected")
    public BookSteps assertBookErrorBodyEqualsExpected(ErrorBody actual, ErrorBody expected) {
        logger.startStep();

        assertErrorBody(actual)
                .addIgnoreField("timestamp")
                .isRecursiveEqualTo(expected);

        logger.stepAction("Asserted ErrorBody", actual)
                .finishStep();

        return this;
    }
}
