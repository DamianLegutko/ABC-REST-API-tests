package pl.damianlegutko.demo.api.test.crud.framework.resource;

import pl.damianlegutko.demo.api.test.crud.framework.exception.CRUDApiTestRuntimeException;

import java.io.InputStream;
import java.util.Scanner;

import static java.lang.Thread.currentThread;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.isNull;

public class ResourceReader {
    private ResourceReader() {
    }

    public static String readFileFromResources(String filePath){
        InputStream inputStream = currentThread()
                .getContextClassLoader()
                .getResourceAsStream(filePath);

        if (isNull(inputStream)){
            throw new CRUDApiTestRuntimeException("File not found: " + filePath);
        }

        try(Scanner scanner = new Scanner(inputStream, UTF_8)){
            scanner.useDelimiter("\\A");

            if (scanner.hasNext()){
                return scanner.next();
            } else {
                return "";
            }
        }
    }
}
