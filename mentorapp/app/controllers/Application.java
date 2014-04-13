package controllers;

import play.mvc.Controller;
import play.*;
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
      return ok(Page1.render("Welcome to Page 1. We are awesome!!!! One step closer baby!!!"));
    }
    
    public static Result homePage(){
    	
    	return ok(homePage.render("this is the home page"));
    }
    
    public static Result userSetup(){
    	return ok(userSetup.render("user setup page"));
    }
    
    public static Result searchCriteria(){
    	return ok(searchCriteria.render("this is search criteria page"));
    }
    
    public static Result searchResults(){
    	return ok(searchResults.render("search result "));
    	
    }
    public static Result viewUserProfile(){
    	return ok(viewUserProfile.render("view user profile page"));
    }
    //testing sync
    //testing again   
    //testing
}
