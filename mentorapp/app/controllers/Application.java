package controllers;

import java.util.ArrayList;
import java.util.Set;

import com.avaje.ebean.Ebean;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import views.html.*;
import models.*;
import models.User;

public class Application extends Controller {
	private static int IDCOUNTER = 0;
	private static final Form<User> userForm = form(User.class);
	
    /**
     * Handle default path requests, redirect to computers list
     */
    public static Result index() {
    	return ok(index.render("HOME PAGE!"));
    }

    public static Result searchCriteria(){
    	return ok(searchCriteria.render("Searchable criteria form page"));
    }
    
    public static Result searchResults(){
    	Set<User> tmp = User.findAll();
    	return ok(searchResults.render(tmp));
    	
    }
    public static Result viewUserProfile(){
    	return ok(viewUserProfile.render("view user profile page"));
    }
    
    public static Result findUser(String aName){
    	return TODO;
    }
    
    public static Result editProfile(){
    	Form<User> computerForm = form(User.class);
        return ok(
            editProfile.render(computerForm)
        );
    }
    
    public static Result save(){
    	Form<User> boundForm = userForm.bindFromRequest();
    	if(boundForm.hasErrors()) {
            return badRequest(editProfile.render(boundForm));
        }
    	User aUser = boundForm.get();
    	User.add(aUser);
    	Ebean.save(User.users);
        return redirect(routes.Application.searchResults());
    }
}
