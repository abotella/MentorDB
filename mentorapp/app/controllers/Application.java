package controllers;

import models.User;
import play.mvc.Controller;
import play.*;
import play.data.Form;
import play.mvc.Result;
import views.html.*;

public class Application extends Controller {
	private static int IDCOUNTER = 0;
	private static final Form<User> userForm = Form.form(User.class);
	
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
   
    
    public static Result homePage(){
    	
    	return ok(homePage.render("this is the home page"));
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
    
    public static Result findUser(String aName){
    	return TODO;
    }
    
    public static Result save(){
    	Form<User> boundForm = userForm.bindFromRequest();
    	User aUser = boundForm.get();
    	User.add(aUser, IDCOUNTER++);
    	return ok(String.format("Saved product %s", aUser));
    }
    public static Result userSetup(){
    	return ok(userSetup.render(userForm));
    }
    //testing sync
    //testing again   
    //testing
}
