package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class Ping extends Controller {

    public Result ping() {
        return ok();
    }
}
