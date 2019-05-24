package services;

import com.typesafe.config.Config;
import models.AuthenticateResponse;
import models.GetAccountsResponse;
import play.libs.Json;
import play.libs.ws.WSClient;

import javax.inject.Inject;
import javax.xml.ws.http.HTTPException;

import java.util.Optional;

import static play.mvc.Http.Status.OK;

/**
 * Bridge is Bankin's SaaS API. This service is where the calls to the API should be implemented.
 *
 * The "userAccountTotalAmount" method doesn't actually do anything yet and needs to be modified to fit the exercice's needs.
 */
public class BridgeClient {

    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private final WSClient wsClient;
    private final String baseUrl;
    private final String apiVersion;
    private final String apiClientId;
    private final String apiClientSecret;

    // these are hardcoded for simplicity's sake
    private static final String USER_EMAIL = "user5@mail.com";
    private static final String USER_PASSWORD = "a!Strongp#assword1";

    @Inject
    public BridgeClient(WSClient wsClient, Config config) {
        this.wsClient = wsClient;
        this.baseUrl = config.getString("bankin.api.baseUrl");
        this.apiVersion = config.getString("bankin.api.version");
        this.apiClientId = config.getString("bankin.api.app.clientId");
        this.apiClientSecret = config.getString("bankin.api.app.clientSecret");
    }

    /**
     * This method is "complete" and doesn't need editing, except if you feel some things could be improved (there
     * is no trap)
     */
    private Optional<AuthenticateResponse> authenticateUser(String email, String password) {
        return wsClient.url(baseUrl + "/authenticate")
                .addHeader("Bankin-Version", apiVersion)
                .addQueryParameter(CLIENT_ID, apiClientId)
                .addQueryParameter(CLIENT_SECRET, apiClientSecret)
                .addQueryParameter("email", email)
                .addQueryParameter("password", password)
                .post("")
                .thenApply(response -> {
                    if (response.getStatus() == OK) {
                        return Optional.of(Json.fromJson(response.asJson(), AuthenticateResponse.class));
                    }
                    return Optional.<AuthenticateResponse>empty();
                })
                .toCompletableFuture()
                .join();
    }

    public double userAccountTotalAmount() throws HTTPException {
        final AuthenticateResponse authenticateResponse = authenticateUser(USER_EMAIL, USER_PASSWORD).orElseThrow(() -> new HTTPException(401));
        return wsClient.url(baseUrl + "/accounts")
                .addHeader("Bankin-Version", apiVersion)
                .addHeader("Authorization", "Bearer " + authenticateResponse.accessToken)
                .addQueryParameter(CLIENT_ID, apiClientId)
                .addQueryParameter(CLIENT_SECRET, apiClientSecret)
                .get()
                .thenApply(response -> {
                    GetAccountsResponse getAccountsResponse = Json.fromJson(response.asJson(), GetAccountsResponse.class);
                    return getAccountsResponse.accounts.stream().mapToDouble(account -> account.balance).sum();
                })
                .toCompletableFuture()
                .join();
    }
}
