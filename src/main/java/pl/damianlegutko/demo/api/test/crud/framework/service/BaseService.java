package pl.damianlegutko.demo.api.test.crud.framework.service;

import lombok.Getter;
import pl.damianlegutko.demo.api.test.crud.framework.config.Config;
import pl.damianlegutko.demo.api.test.crud.framework.retrofit.RetrofitFactory;

public abstract class BaseService implements Serviceable {
    @Getter
    Config config;
    @Getter
    RetrofitFactory retrofitFactory;

    public BaseService(Config config, RetrofitFactory retrofitFactory) {
        this.config = config;
        this.retrofitFactory = retrofitFactory;
    }
}
