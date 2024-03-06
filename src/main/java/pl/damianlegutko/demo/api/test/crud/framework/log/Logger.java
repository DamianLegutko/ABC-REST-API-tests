package pl.damianlegutko.demo.api.test.crud.framework.log;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import pl.damianlegutko.demo.api.test.crud.framework.data.Jsonable;

import java.util.List;

import static io.qameta.allure.Allure.addAttachment;
import static java.lang.String.valueOf;
import static java.lang.System.out;
import static pl.damianlegutko.demo.api.test.crud.framework.log.StackTracker.getNameForAboveMethod;

@Slf4j
public class Logger {
    public static final String SCENARIO_BEGIN = "################################# S C E N A R I O #################################\n";
    public static final String SCENARIO_END = "###################################################################################\n";
    public static final String STEP_BEGIN = "-----################################ S T E P #####################################\n";
    public static final String STEP_END = "-----##############################################################################\n";
    public static final String REQUEST_BEGIN = "----------######################## R E Q U E S T ##################################\n";
    public static final String REQUEST_END = "------------#######################################################################\n";
    public static final String SEPARATOR = "\n# ---------------------------------------------------------------------------------\n";
    static Integer requestNumber = 1;
    static Integer stepNumber = 1;

    public Logger request(String requestName, StringBuilder message) {
        return request(requestName, message.toString());
    }

    public Logger request(String requestName, String message) {
        String logName = new StringBuilder()
                .append("REQUEST ")
                .append(requestNumber)
                .append(": ")
                .append(requestName)
                .toString();

        addAttachment(logName, message);

        requestNumber += 1;

        return this;
    }

    public Logger startScenario() {
        String stepStartMessage = new StringBuilder()
                .append("START SCENARIO ")
                .append(": ")
                .append(getNameForAboveMethod())
                .toString();

        out.print("\n");
        out.print(SCENARIO_BEGIN);
        out.println(stepStartMessage);

        return this;
    }

    public Logger finishScenario() {
        String stepFinishMessage = new StringBuilder()
                .append("FINISH SCENARIO ")
                .append(": ")
                .append(getNameForAboveMethod())
                .toString();

        out.print(stepFinishMessage);
        out.println(SCENARIO_END);
        out.print("\n");

        return this;
    }

    public Logger startStep() {
        String stepStartMessage = new StringBuilder()
                .append("START STEP ")
                .append(": ")
                .append(getNameForAboveMethod())
                .toString();

        out.print("\n");
        out.print(STEP_BEGIN);
        out.println(stepStartMessage);

        stepNumber = 1;

        return this;
    }

    public Logger finishStep() {
        String stepFinishMessage = new StringBuilder()
                .append("FINISH STEP ")
                .append(": ")
                .append(getNameForAboveMethod())
                .toString();

        out.println(stepFinishMessage);
        out.print(STEP_END);
        out.print("\n");

        stepNumber = 1;

        return this;
    }

    public Logger stepAction(String stepName, String message) {
        String logName = new StringBuilder()
                .append("STEP ACTION ")
                .append(stepNumber)
                .append(": ")
                .append(stepName)
                .toString();

        if (stepNumber > 1) {
            out.println(SEPARATOR);
        }

        out.println(message);

        addAttachment(logName, message);
        stepNumber += 1;

        return this;
    }

    public Logger stepAction(String actionTitle, Integer number) {
        return stepAction(actionTitle, valueOf(number));
    }

    public Logger stepAction(String actionTitle, Jsonable jsonable) {
        return stepAction(actionTitle, jsonable.toJson());
    }

    public Logger stepAction(String actionTitle, @NonNull List<Integer> list) {
        StringBuilder message = new StringBuilder();
        list.forEach(message::append);

        return stepAction(actionTitle, message.toString());
    }
}
