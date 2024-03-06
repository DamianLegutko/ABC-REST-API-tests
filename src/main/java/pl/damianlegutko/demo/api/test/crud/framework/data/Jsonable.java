package pl.damianlegutko.demo.api.test.crud.framework.data;

import pl.damianlegutko.demo.api.test.crud.framework.serializer.Serializer;

public interface Jsonable {
    default String toJson() {
        return Serializer.toJson(this);
    }
}
