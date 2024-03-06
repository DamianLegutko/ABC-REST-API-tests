package pl.damianlegutko.demo.api.test.crud.framework;

import com.github.javafaker.Faker;
import com.google.inject.Inject;
import lombok.Getter;
import pl.damianlegutko.demo.api.test.crud.framework.config.Config;

public class DataGenerator {
    @Getter
    Faker generate;

    @Inject
    public DataGenerator(Config config) {
        generate = new Faker(config.getDataGenerator().getLocale());
    }
}
