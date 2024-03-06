package pl.damianlegutko.demo.api.test.crud.framework.log;

import static java.lang.Thread.currentThread;

public class StackTracker {
    private static StackTraceElement[] getStackTrace() {
        return currentThread()
                .getStackTrace();
    }

    private static StackTraceElement getAboveMethod() {
        return getStackTrace()[5];
    }

    static String getNameForAboveMethod() {
        return getAboveMethod()
                .getMethodName()
                .replaceAll("([A-Z])", " $1")
                .toLowerCase();
    }
}
