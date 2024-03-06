package pl.damianlegutko.demo.api.test.crud.domain.custom;


import com.google.inject.Inject;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Tag;
import pl.damianlegutko.demo.api.test.crud.framework.base.EndpointValidationTests;

@Tag("Custom")
@Owner("Damian Legutko")
@Epic("My Custom service")
public class CustomEndpointValidationTest extends EndpointValidationTests {
    @Inject
    protected CustomService service;
}
