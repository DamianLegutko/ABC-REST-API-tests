package pl.damianlegutko.demo.api.test.crud.framework.exception;

public class CRUDApiTestRuntimeException extends RuntimeException {
    public CRUDApiTestRuntimeException(String message) {
        super(message);
    }

    public CRUDApiTestRuntimeException(Throwable cause) {
        super(cause);
    }

    public CRUDApiTestRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
