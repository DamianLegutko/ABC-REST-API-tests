package pl.damianlegutko.demo.api.test.crud.domain.books.api.v1.books;

import pl.damianlegutko.demo.api.test.crud.framework.assertion.BaseAssert;

public class BookAssert extends BaseAssert<BookAssert, Book> {
    public BookAssert(Book actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public static BookAssert assertBook(Book actual){
        return new BookAssert(actual, BookAssert.class);
    }
}
