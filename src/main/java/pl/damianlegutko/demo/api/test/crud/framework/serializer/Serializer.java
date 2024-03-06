package pl.damianlegutko.demo.api.test.crud.framework.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import pl.damianlegutko.demo.api.test.crud.framework.exception.CRUDApiTestRuntimeException;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_ENUMS_USING_TO_STRING;

public class Serializer {
    private static final String SERIALIZATION_ERROR_MESSAGE = "Cannot serialize this object";
    static JsonMapper jsonMapper = (JsonMapper) new JsonMapper()
            .registerModule(new JavaTimeModule())
            .setSerializationInclusion(NON_NULL)
            .enable(INDENT_OUTPUT)
            .enable(WRITE_ENUMS_USING_TO_STRING);

    static SimpleModule yamlMapperModule = new SimpleModule();

    static ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory())
            .registerModule(yamlMapperModule);

    private Serializer() {
    }

    public static String toJson(Object objectToSerialize) {
        try {
            return jsonMapper.writeValueAsString(objectToSerialize);
        } catch (JsonProcessingException exception) {
            throw new CRUDApiTestRuntimeException("Problem with serialization", exception);
        }
    }

    public static <T> T deepClone(Object objectToSerialize, Class<T> cloneToClass) {
        String objectAsJson = toJson(objectToSerialize);
        return toObjectFromJson(objectAsJson, cloneToClass);
    }

    public static <T> T toObjectFromJson(String json, Class<T> clazz) {
        try {
            return jsonMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new CRUDApiTestRuntimeException(SERIALIZATION_ERROR_MESSAGE);
        }
    }

    public static <T> T toObjectFromYaml(String yaml, Class<T> clazz) {
        try {
            return yamlMapper.readValue(yaml, clazz);
        } catch (JsonProcessingException e) {
            throw new CRUDApiTestRuntimeException(SERIALIZATION_ERROR_MESSAGE, e);
        }
    }

    public static String makeItPretty(String json) {
        try {
            JsonNode jsonNode = jsonMapper.readTree(json);

            return jsonMapper.writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            throw new CRUDApiTestRuntimeException(e);
        }
    }
}
