package pl.damianlegutko.demo.api.test.crud.domain.books.common.error;

import pl.damianlegutko.demo.api.test.crud.framework.assertion.BaseAssert;

public class ErrorBodyAssert extends BaseAssert<ErrorBodyAssert, ErrorBody> {
    public ErrorBodyAssert(ErrorBody actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public static ErrorBodyAssert assertErrorBody(ErrorBody actual) {
        return new ErrorBodyAssert(actual, ErrorBodyAssert.class);
    }
}