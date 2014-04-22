package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.avaje.ebean.Ebean;

import play.mvc.*;
import play.data.*;
import play.db.ebean.Model.Finder;
import static play.data.Form.*;
import views.html.*;
import models.*;

public class UserApplication extends Controller {
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
    	List<User> tmp = find().findList();
    	return ok(searchResults.render(tmp));
    	
    }
    public static Result viewUserProfile(){
    	return ok(viewUserProfile.render("view user profile page"));
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
        return redirect(routes.UserApplication.searchResults());
    }
    
    public static Finder<Long, User> find() {
		return new Finder<Long, User>(Long.class, User.class);
	}
}
