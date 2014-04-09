package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
    	//CHecking if this is working
    	//LETS GET IT ON!!!
        return ok(index.render("Your new application is ready. Is it really ready??"));
    }

}
