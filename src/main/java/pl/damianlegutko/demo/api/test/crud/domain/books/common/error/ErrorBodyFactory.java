package pl.damianlegutko.demo.api.test.crud.domain.books.common.error;

import com.google.inject.Inject;
import pl.damianlegutko.demo.api.test.crud.framework.log.Logger;

import static pl.damianlegutko.demo.api.test.crud.domain.books.BookApi.RESOURCE_PATH;

public class ErrorBodyFactory {
    Logger logger;

    @Inject
    public ErrorBodyFactory(Logger logger) {
        this.logger = logger;
    }

    public ErrorBody objectNotFound(String path) {
        ErrorBody errorBody = new ErrorBody()
                .setPath(path)
                .setError("Not Found")
                .setStatus(404);

        logger.stepAction("Generated ErrorBody", errorBody);

        return errorBody;
    }

    public ErrorBody bookNotFound(Integer bookId) {
        return objectNotFound("/" + RESOURCE_PATH + "/" + bookId);
    }

    public ErrorBody missingRequiredField(String path, String missingFieldName) {
        ErrorBody errorBody = new ErrorBody()
                .setPath(path)
                .setError("Missing required field: " + missingFieldName.trim())
                .setStatus(400);

        logger.stepAction("Generated ErrorBody", errorBody);

        return errorBody;
    }
}
