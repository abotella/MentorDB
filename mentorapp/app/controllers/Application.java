package controllers;

import play.mvc.Controller;
import play.data.Form;
import play.mvc.Result;
import views.html.Index;
import views.html.Page1;

public class Application extends Controller {

	/**
     * Returns the home page. 
     * @return The resulting home page. 
     */
    public static Result index() {
      return ok(Index.render("Testing: This is the homepage."));
    }

    
    /**
     * Returns page1.
     * @return The Page1.
     */
    public static Result page1() {
      return ok(Page1.render("Welcome to Page 1."));
    }
    //testing sync
    //testing again
}
