package pl.damianlegutko.demo.api.test.crud.framework.config;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@NoArgsConstructor
@Getter
public class Config {
    final Map<String, ServiceConfig> services = new HashMap<>();
    DataGeneratorConfig dataGenerator = new DataGeneratorConfig();
    AllureConfig allure = new AllureConfig();

    public Optional<ServiceConfig> getService(String serviceName) {
        return this.services.keySet()
                .stream()
                .filter((key) -> key.equalsIgnoreCase(serviceName))
                .findFirst()
                .map(services::get);
    }
}
