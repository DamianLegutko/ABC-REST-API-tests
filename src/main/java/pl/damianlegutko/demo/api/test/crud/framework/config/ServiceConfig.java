package pl.damianlegutko.demo.api.test.crud.framework.config;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import pl.damianlegutko.demo.api.test.crud.framework.exception.CRUDApiTestConfigException;

import java.util.List;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

@Getter
public class ServiceConfig {
    ServiceUrlConfig url;
    List<ServiceUserConfig> users;

    private void setUsers(List<ServiceUserConfig> users) {
        if (nonNull(users)) {
            if (getDefaultUsers(users).size() > 1)
                throw new CRUDApiTestConfigException("You cannot set more than 1 default user for the same service.");
        }

        this.users = users;
    }

    @NotNull
    private List<ServiceUserConfig> getDefaultUsers(List<ServiceUserConfig> users) {
        return users.stream()
                .filter(user -> user.getIsDefault() != null && user.getIsDefault())
                .collect(toList());
    }

    public ServiceUserConfig getDefaultUserConfig() {
        if (users.size() == 0)
            throw new CRUDApiTestConfigException("You have to configure at least 1 user for this service to get the default one.");

        List<ServiceUserConfig> defaultUsers = getDefaultUsers(users);

        return defaultUsers.size() > 0
                ? defaultUsers.get(0)
                : users.get(0);
    }
}
