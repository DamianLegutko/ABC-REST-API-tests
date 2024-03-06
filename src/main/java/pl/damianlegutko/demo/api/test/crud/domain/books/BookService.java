package pl.damianlegutko.demo.api.test.crud.domain.books;

import com.google.inject.Inject;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import pl.damianlegutko.demo.api.test.crud.domain.books.api.v1.books.Book;
import pl.damianlegutko.demo.api.test.crud.framework.config.Config;
import pl.damianlegutko.demo.api.test.crud.framework.retrofit.Request;
import pl.damianlegutko.demo.api.test.crud.framework.retrofit.RetrofitFactory;
import pl.damianlegutko.demo.api.test.crud.framework.service.BaseService;

import java.util.List;

public class BookService extends BaseService {
    final BookApi api;

    @Inject
    public BookService(Config config, RetrofitFactory retrofitFactory) {
        super(config, retrofitFactory);

        api = getApiInstance(BookApi.class);
    }

    @Override
    public String getServiceName() {
        return "LuxsoftBooks";
    }

    @NotNull
    public Request<Book> createBookRequest(String token, Book book) {
        return new Request<>(api.createBook(token, book));
    }

    @NotNull
    public Request<Book> createBookRequest(Book book) {
        return createBookRequest(getDefaultToken(), book);
    }

    @NotNull
    public Request<Book> updateBookRequest(String token, Book book, Integer bookIdToUpdate) {
        return new Request<>(api.updateBook(token, book, bookIdToUpdate));
    }

    @NotNull
    public Request<Book> updateBookRequest(Book book, Integer bookIdToUpdate) {
        return updateBookRequest(getDefaultToken(), book, bookIdToUpdate);
    }

    @NotNull
    public Request<Book> updateBookRequest(String token, Book book) {
        return updateBookRequest(token, book, book.getId());
    }

    @NotNull
    public Request<Book> updateBookRequest(Book book) {
        return updateBookRequest(book, book.getId());
    }

    @NotNull
    public Request<List<Book>> getBooksRequest(String token) {
        return new Request<>(api.getBooks(token));
    }

    @NotNull
    public Request<List<Book>> getBooksRequest() {
        return getBooksRequest(getDefaultToken());
    }

    @NotNull
    public Request<Book> getBookByIdRequest(String token, Integer id) {
        return new Request<>(api.getBookById(token, id));
    }

    @NotNull
    public Request<Book> getBookByIdRequest(Integer id) {
        return getBookByIdRequest(getDefaultToken(), id);
    }

    @NotNull
    private Request<ResponseBody> deleteBookRequest(String token, Integer id) {
        return new Request<>(api.deleteBook(token, id));
    }

    @NotNull
    public Request<ResponseBody> deleteBookRequest(Integer id) {
        return deleteBookRequest(getDefaultToken(), id);
    }
}
