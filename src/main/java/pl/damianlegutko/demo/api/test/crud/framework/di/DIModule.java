package pl.damianlegutko.demo.api.test.crud.framework.di;

import com.google.inject.AbstractModule;
import pl.damianlegutko.demo.api.test.crud.framework.config.Config;
import pl.damianlegutko.demo.api.test.crud.framework.config.ConfigReader;

public class DIModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Config.class).toInstance(ConfigReader.readConfig());
    }
}
