package pl.damianlegutko.demo.api.test.crud.framework.retrofit;

import okhttp3.ResponseBody;
import org.apache.commons.lang3.NotImplementedException;
import pl.damianlegutko.demo.api.test.crud.framework.exception.CRUDApiTestRequestException;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.damianlegutko.demo.api.test.crud.framework.serializer.Serializer.toObjectFromJson;

public class Request<Payload> {
    Call<Payload> call;

    public Request(Call<Payload> call) {
        this.call = call;
    }

    public Response<Payload> execute() {
        try {
            return call.execute();
        } catch (IOException e) {
            throw new CRUDApiTestRequestException(e);
        }
    }

    public Response<Payload> executeSuccessful() {
        Response<Payload> response = execute();

        assertThat(response.isSuccessful())
                .as("The query was expected to return response code from 200 to 299 response code.")
                .isNotNull()
                .isTrue();

        return response;
    }

    public Payload getResponseBodyOnSuccess() {
        return executeSuccessful()
                .body();
    }

    public String getPlanTextStringOnSuccess() {
        Payload responseBodyOnSuccess = getResponseBodyOnSuccess();

        try {
            return ((ResponseBody) responseBodyOnSuccess)
                    .string();
        } catch (IOException e) {
            throw new CRUDApiTestRequestException(e);
        }
    }

    public Response<Payload> executeUnsuccessful() {
        Response<Payload> response = execute();

        assertThat(response.isSuccessful())
                .as("It was expected that the query would NOT return response code from 200 to 299 response code.")
                .isNotNull()
                .isFalse();

        return response;
    }

    public ResponseBody getErrorBodyOnFailure() {
        return executeUnsuccessful()
                .errorBody();
    }

    public <ErrorResponse> ErrorResponse getErrorBodyOnFailure(Class<ErrorResponse> errorResponseBodyClass) {
        try {
            return toObjectFromJson(getErrorBodyOnFailure().string(), errorResponseBodyClass);
        } catch (IOException e) {
            throw new CRUDApiTestRequestException(e);
        }
    }

    /*
    TODO
     */
    public void executeUntil(Predicate<Payload> condition) {
        while (!condition.test(getResponseBodyOnSuccess())) {
            throw new NotImplementedException("Not implemented yet");
        }
    }
}
