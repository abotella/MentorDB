package controllers;

import play.mvc.Controller;
import play.data.Form;
import play.mvc.Result;
import views.html.*;

public class Application extends Controller {

	/**
     * Returns the home page. 
     * @return The resulting home page. 
     */
    public static Result index() {
      return ok(index.render("Testing: This is the homepage."));
    }

    
    /**
     * Returns page1.
     * @return The Page1.
     */
    public static Result page1() {
      return ok(page1.render("Welcome to Page 1. We are awesome!!!! One step closer baby!!!"));
    }
    //testing sync
    //testing again
}
