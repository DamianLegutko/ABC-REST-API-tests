package pl.damianlegutko.demo.api.test.crud.framework.config;

import static pl.damianlegutko.demo.api.test.crud.framework.resource.ResourceReader.readFileFromResources;
import static pl.damianlegutko.demo.api.test.crud.framework.serializer.Serializer.toObjectFromYaml;

public class ConfigReader {
    private ConfigReader() {
    }

    public static Config readConfig() {
        return toObjectFromYaml(readFileFromResources("config.yml"), Config.class);
    }
}
