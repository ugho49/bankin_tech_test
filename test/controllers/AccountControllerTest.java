package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

public class AccountControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void should_respond_http_200() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/account/total-amount");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

    @Test
    public void should_get_the_correct_amount() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/account/total-amount");

        Result result = route(app, request);
        final JsonNode body = Json.parse(contentAsString(result));
        assertEquals(156837, body.get("amount").intValue());
    }
}
