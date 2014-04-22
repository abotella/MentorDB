package controllers;
/**
 * The purpose of this class is to render information from Java to HTML
 */
import java.util.ArrayList;
import java.util.HashSet;
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
     * Index page
     */
    public static Result index() {
    	return ok(index.render("HOME PAGE!"));
    }
    
    /**
     * Search page
     * @return SearchCriteria page loaded
     */
    public static Result searchCriteria(){
    	return ok(searchCriteria.render("Searchable criteria form page"));
    }
    
    /**
     * Results page
     * @return SearchResults page loaded
     */
    public static Result searchResults(){
    	List<User> tmp = find().findList();
    	return ok(searchResults.render(tmp));
    	
    }
    
    /**
     * User profile
     * @return UserProfile page is loaded
     */
    public static Result viewUserProfile(){
    	return ok(viewUserProfile.render("view user profile page"));
    }

    /**
     *  User profile edit page
     * @return user profile edit page is loaded
     */
    public static Result editProfile(){
    	Form<User> computerForm = form(User.class);
        return ok(editProfile.render(computerForm));
    }
    
    /**
     * Saves the form
     * @return redirected to user page
     */
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
    
    /**
     * Finds all users in the database
     * @return list of users
     */
    public static Finder<Long, User> find() {
		return new Finder<Long, User>(Long.class, User.class);
	}
    
    /**
     * Find by user name
     * @param term
     * @return a user name profile information
     */
    public static Set<User> findByName(String term) {
		final Set<User> results = new HashSet<User>();
		for (User candidate : User.users) {
			if (candidate.userName.toLowerCase().contains(term.toLowerCase())) {
				results.add(candidate);
			}
		}
		return results;
	}
}
