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
<<<<<<< HEAD
    
    /**
     * Returns page1.
     * @return The Page1.
     */
    public static Result page1() {
      return ok(Page1.render("Welcome to Page 1."));
    }
    
=======
    //is this making a change?
>>>>>>> 48d7871577235ad7a2f3608f646a81e6563bc138
}
