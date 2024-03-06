package pl.damianlegutko.demo.api.test.crud.framework.config;

import lombok.Getter;
import pl.damianlegutko.demo.api.test.crud.framework.auth.AuthType;
import pl.damianlegutko.demo.api.test.crud.framework.auth.BasicAuth;
import pl.damianlegutko.demo.api.test.crud.framework.auth.Tokenable;
import pl.damianlegutko.demo.api.test.crud.framework.exception.CRUDApiTestConfigException;

import static java.lang.String.format;

@Getter
public class ServiceUserConfig {
    Boolean isDefault;
    AuthType authType;
    String username;
    String password;

    public Tokenable getTokenableObject() {
        switch (authType) {
            case BASIC_AUTH:
                return new BasicAuth(username, password);
            default:
                throw new CRUDApiTestConfigException(format("Authorization type %s is not implemented yet.", authType));
        }
    }
}
