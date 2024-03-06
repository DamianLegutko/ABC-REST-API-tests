package pl.damianlegutko.demo.api.test.crud.domain.books;

import com.google.inject.Inject;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Tag;
import pl.damianlegutko.demo.api.test.crud.domain.books.api.v1.books.BookFactory;
import pl.damianlegutko.demo.api.test.crud.domain.books.common.error.ErrorBodyFactory;
import pl.damianlegutko.demo.api.test.crud.framework.base.EndpointValidationTests;

@Tag("Books")
@Owner("Damian Legutko")
@Epic("Book service")
public class BookEndpointValidationTest extends EndpointValidationTests {
    @Inject
    protected BookScenarios scenario;
    @Inject
    protected BookSteps step;
    @Inject
    protected BookService service;
    @Inject
    protected ErrorBodyFactory errorBodyFactory;
    @Inject
    protected BookFactory bookFactory;
}
