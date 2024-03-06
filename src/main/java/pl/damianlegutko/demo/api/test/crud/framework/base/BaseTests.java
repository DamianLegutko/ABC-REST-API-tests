package pl.damianlegutko.demo.api.test.crud.framework.base;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.qameta.allure.Link;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import pl.damianlegutko.demo.api.test.crud.framework.di.DIModule;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Link(name = "Author website", url = "http://damianlegutko.pl/")
@Link(name = "Framework repository", url = "https://github.com/DamianLegutko")
public abstract class BaseTests {
    protected Injector injector = Guice.createInjector(new DIModule());

    @BeforeEach
    public void setup() {
        injector.injectMembers(this);
    }
}
