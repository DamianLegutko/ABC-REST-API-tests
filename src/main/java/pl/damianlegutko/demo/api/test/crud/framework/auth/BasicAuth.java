package pl.damianlegutko.demo.api.test.crud.framework.auth;

import okhttp3.Credentials;

import java.util.Base64;

import static java.util.Base64.getEncoder;

public class BasicAuth implements Tokenable {
    final String username;
    final String password;

    public BasicAuth(String username, String password) {
        this.username = username;
        this.password = password;
    }

/*    @Override
    public String getAuthorizationToken() {
        String authString = username + ":" + password;

        byte[] decodedBytes = Base64.getEncoder().encode(authString.getBytes());
        String encodedAuthString = new String(decodedBytes);

        return "Basic " + encodedAuthString;
    }*/
/*    @Override
    public String getAuthorizationToken() {
        String authString = username + ":" + password;
        String encodedAuthString = Base64.getEncoder().encodeToString(authString.getBytes());
        return "Basic " + encodedAuthString;
    }    */
    @Override
    public String getAuthorizationToken() {
        return Credentials.basic(username,password);
    }
}
