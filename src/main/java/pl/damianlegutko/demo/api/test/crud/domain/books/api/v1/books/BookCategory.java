package pl.damianlegutko.demo.api.test.crud.domain.books.api.v1.books;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import static java.util.Arrays.stream;

public enum BookCategory {
    PROGRAMMING("Programming"),
    NEW_AGE("New Age");

    @Getter
    @JsonValue
    String value;

    BookCategory(String value) {
        this.value = value;
    }

    public static boolean hasValueOf(String value) {
        return stream(values())
                .anyMatch(bookCategory -> bookCategory.getValue().equalsIgnoreCase(value));
    }
}
