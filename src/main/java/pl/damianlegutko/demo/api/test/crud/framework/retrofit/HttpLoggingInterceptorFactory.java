package pl.damianlegutko.demo.api.test.crud.framework.retrofit;

import com.google.inject.Inject;
import okhttp3.logging.HttpLoggingInterceptor;
import pl.damianlegutko.demo.api.test.crud.framework.config.Config;
import pl.damianlegutko.demo.api.test.crud.framework.log.Logger;

import static java.lang.System.out;
import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;
import static pl.damianlegutko.demo.api.test.crud.framework.log.Logger.*;
import static pl.damianlegutko.demo.api.test.crud.framework.serializer.Serializer.makeItPretty;


public class HttpLoggingInterceptorFactory {
    static StringBuilder requestLog = new StringBuilder();
    static boolean isEndOfRequest = false;
    static String requestName = "";

    Config config;
    Logger logger;

    @Inject
    public HttpLoggingInterceptorFactory(Config config, Logger logger) {
        this.config = config;
        this.logger = logger;
    }

    HttpLoggingInterceptor createHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> {
            StringBuilder logMessage = new StringBuilder("# ");

            isEndOfRequest = false;

            if (!message.equals("")) {
                if (message.startsWith("{") || message.startsWith("[")) {
                    logMessage.append("\n\n")
                            .append(makeItPretty(message))
                            .append("\n");
                } else if (message.startsWith("--> ") && !message.startsWith("--> END")) {
                    logMessage.append("\n")
                            .append(REQUEST_BEGIN)
                            .append("# ")
                            .append(message);
                    requestName = message;
                } else if (message.startsWith("--> END")) {
                    logMessage.append(message)
                            .append(SEPARATOR);
                } else if (message.startsWith("<-- END HTTP")) {
                    logMessage.append(message)
                            .append(REQUEST_END);
                    isEndOfRequest = true;
                } else {
                    logMessage.append(message);
                }

                out.println(logMessage);
                requestLog.append(logMessage)
                        .append("\n");

                if (isEndOfRequest && config.getAllure().getAddLogsToReport()) {
                    logger.request(requestName, requestLog);
                    requestLog = new StringBuilder();
                }
            }
        });

        interceptor.setLevel(BODY);

        return interceptor;
    }
}
