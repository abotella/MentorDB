package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
    	//CHecking if this is working
        return ok(index.render("Your new application is ready."));
    }

}
