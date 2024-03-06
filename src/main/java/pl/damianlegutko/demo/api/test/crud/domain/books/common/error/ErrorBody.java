package pl.damianlegutko.demo.api.test.crud.domain.books.common.error;

import lombok.Data;
import pl.damianlegutko.demo.api.test.crud.framework.data.Resourceable;

@Data
public class ErrorBody implements Resourceable {
    String timestamp;
    int status;
    String error;
    String path;

    @Override
    public Object deepClone() {
        return deepClone(ErrorBody.class);
    }
}
