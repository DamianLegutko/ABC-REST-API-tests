package pl.damianlegutko.demo.api.test.crud.framework.data;

import pl.damianlegutko.demo.api.test.crud.framework.serializer.Serializer;

public interface Cloneable {
    default <T> T deepClone(Class<T> clazz) {
        return Serializer.deepClone(this, clazz);
    }

    Object deepClone();
}
