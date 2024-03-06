package pl.damianlegutko.demo.api.test.crud.framework.exception;

public class CRUDApiTestConfigException extends CRUDApiTestRuntimeException {
    public CRUDApiTestConfigException(String message) {
        super(message);
    }

    public CRUDApiTestConfigException(Throwable cause) {
        super(cause);
    }

    public CRUDApiTestConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
