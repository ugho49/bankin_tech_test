package controllers;

import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;

public class PingTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void ping_should_respond_http_200_for_health_check() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/ping");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

}
