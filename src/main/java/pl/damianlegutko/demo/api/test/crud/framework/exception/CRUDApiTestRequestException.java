package pl.damianlegutko.demo.api.test.crud.framework.exception;

public class CRUDApiTestRequestException extends CRUDApiTestRuntimeException {
    public CRUDApiTestRequestException(String message) {
        super(message);
    }

    public CRUDApiTestRequestException(Throwable cause) {
        super(cause);
    }

    public CRUDApiTestRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
