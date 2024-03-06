package pl.damianlegutko.demo.api.test.crud.domain.books;

import okhttp3.ResponseBody;
import pl.damianlegutko.demo.api.test.crud.domain.books.api.v1.books.Book;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface BookApi {
    String RESOURCE_PATH = "api/v1/books";

    @GET(RESOURCE_PATH)
    Call<List<Book>> getBooks(@Header("Authorization") String authorizationToken);

    @GET(RESOURCE_PATH + "/{id}")
    Call<Book> getBookById(@Header("Authorization") String authorizationToken,
                           @Path("id") Integer id);

    @Headers({
            "Accept: */*",
            "Cache-Control: no-cache",
            "Accept: text/plain"
    })
    @DELETE(RESOURCE_PATH + "/{id}")
    Call<ResponseBody> deleteBook(@Header("Authorization") String authorizationToken,
                                  @Path("id") Integer id);

    @Headers({
            "Accept: */*",
            "Cache-Control: no-cache",
            "Accept: text/plain"
    })

    @POST(RESOURCE_PATH)
    Call<Book> createBook(@Header("Authorization") String authorizationToken,
                          @Body Book book);

    @PUT(RESOURCE_PATH + "/{id}")
    Call<Book> updateBook(@Header("Authorization") String authorizationToken,
                          @Body Book book,
                          @Path("id") Integer id);
}
