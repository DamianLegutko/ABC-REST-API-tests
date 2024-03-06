package pl.damianlegutko.demo.api.test.crud.framework.service;

import pl.damianlegutko.demo.api.test.crud.framework.auth.Tokenable;
import pl.damianlegutko.demo.api.test.crud.framework.config.Config;
import pl.damianlegutko.demo.api.test.crud.framework.config.ServiceConfig;
import pl.damianlegutko.demo.api.test.crud.framework.retrofit.RetrofitFactory;

public interface Serviceable {
    Config getConfig();

    String getServiceName();

    RetrofitFactory getRetrofitFactory();

    default <T> T getApiInstance(Class<T> tClass) {
        return getRetrofitFactory()
                .createRetrofitInstance(getBaseUrl())
                .create(tClass);
    }

    default ServiceConfig getServiceConfig() {
        return getConfig()
                .getService(getServiceName())
                .get();
    }

    default String getBaseUrl() {
        return getServiceConfig()
                .getUrl()
                .getBaseUrl();
    }

    default Tokenable getDefaultCredentials() {
        return getServiceConfig()
                .getDefaultUserConfig()
                .getTokenableObject();
    }

    default String getDefaultToken() {
        return getDefaultCredentials()
                .getAuthorizationToken();
    }
}
