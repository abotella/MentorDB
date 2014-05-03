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
import scala.collection.parallel.ParIterableLike.Find;
import static play.data.Form.*;
import views.html.*;
import models.*;

public class UserApplication extends Controller {
	private static final Form<User> userForm = form(User.class);
	private static final Form<User> searchForm = form(User.class);
	
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
    	Form<User> aForm = form(User.class);
    	return ok(searchCriteria.render(aForm));
    }
    
    /**
     * Results page
     * @return SearchResults page loaded
     */
    public static Result searchResults(){
    	String t = form().bindFromRequest().get("currentTitle");
    	String e = form().bindFromRequest().get("currentEmployer");
    	List<User> titles = findByCriteria(t);
    	List<User> employers = findByCriteria(e);
    	
    	return ok(searchResults.render(titles,employers));
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
        return redirect(routes.UserApplication.editProfile());
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
    public static User findByName(String term) {
		final List<User> results = find().findList();
		for (User candidate : results) {
			if (candidate.userName.toLowerCase().contains(term.toLowerCase())) {
				return candidate;
			}
		}
		return null;
	}
    
    /**
     * Find potential candidates based on criteria selected
     * @param someCriteria criteria selected
     * @return candidates list of candidates that contains at least one criteria
     */
    public static List<User> findByCriteria(String someCriteria) {
    	List<User> candidates = new ArrayList<User>();
    	List<User> list = find().findList();
    	
    	for (User aUser : list) {
			if (aUser.contains(someCriteria)){
				candidates.add(aUser);
			}
		}

    	return candidates;
    }
    
    public static Result userProfile(){
    	List<User> tmp = find().findList();
    	return ok(userProfile.render(tmp));
    }
    
    public static Finder<Long, User> aFind = new Finder<Long, User>(Long.class, User.class);
}
