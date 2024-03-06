package pl.damianlegutko.demo.api.test.crud.framework.exception;

public class CRUDApiTestException extends Exception{
    public CRUDApiTestException(String message) {
        super(message);
    }

    public CRUDApiTestException(Throwable cause) {
        super(cause);
    }

    public CRUDApiTestException(String message, Throwable cause) {
        super(message, cause);
    }
}
