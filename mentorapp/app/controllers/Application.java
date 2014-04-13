package controllers;

import models.User;
import play.mvc.Controller;
import play.data.*;
//import play.data.Form;
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
    	
    	return ok();
    }
    
    public static Result userSetup(){
    	return ok(userSetup.render(userForm));
    }
    
    public static Result searchCriteria(){
    	return ok();
    }
    
    public static Result searchResults(){
    	return ok();
    	
    }
    public static Result viewUserProfile(){
    	return ok();
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
    //testing sync
    //testing again   
    //testing
}
